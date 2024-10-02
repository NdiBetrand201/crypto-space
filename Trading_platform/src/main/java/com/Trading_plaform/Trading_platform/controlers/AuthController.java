package com.Trading_plaform.Trading_platform.controlers;

import com.Trading_plaform.Trading_platform.config.JwtProvider;
import com.Trading_plaform.Trading_platform.models.TwoFactorAuth;
import com.Trading_plaform.Trading_platform.models.TwoFactorOTP;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.repository.UserRepository;
import com.Trading_plaform.Trading_platform.response.AuthResponse;
import com.Trading_plaform.Trading_platform.services.CustomerUserDetailsServuce;
import com.Trading_plaform.Trading_platform.services.EmailService;
import com.Trading_plaform.Trading_platform.services.TwoFactorOTPService;
import com.Trading_plaform.Trading_platform.utils.OtPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class AuthController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TwoFactorOTPService twoFactorOTPService;

    @Autowired
    private CustomerUserDetailsServuce customerUserDetailsServuce;


    @PostMapping("/auth/signup")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) throws Exception {

        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist != null) {
            throw new Exception("Email already exist. please use another email");
        } else {
            User createdUser = new User();
            createdUser.setEmail(user.getEmail());
            createdUser.setPassword(user.getPassword());
            createdUser.setFullName(user.getFullName());
            createdUser.setMobile(user.getMobile());

            User savedUser = userRepository.save(createdUser);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwt = JwtProvider.generateToken(auth);

            AuthResponse res = new AuthResponse();
            res.setJwt(jwt);
            res.setStatus(true);
            res.setMessage("Register SUCCESSFUL");
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user) throws Exception {
          String password=user.getPassword();
          String email=user.getEmail();
         User authUser=userRepository.findByEmail(email);

            Authentication auth =authenticate(password, email);
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwt = JwtProvider.generateToken(auth);



            if(user.getTwoFactorAuth().isEnabled()) {
                AuthResponse res=new AuthResponse();

                res.setMessage("Two factor authentication is enabled");
                res.setTwoFactorAuthEnabled(true);
                String otp = OtPUtils.generateOTP();

                TwoFactorOTP oldTwoFactorOTP = twoFactorOTPService.findByUser(authUser.getId());

                if(oldTwoFactorOTP!=null){
                    twoFactorOTPService.deleteTwoFactorOTP(oldTwoFactorOTP);
                }
                TwoFactorOTP newFactorOTP = twoFactorOTPService.createTwoFactorOTP(
                        authUser,otp,jwt
                );

                emailService.sendVerificationOTPEmail(email,otp);

             res.setSession(newFactorOTP.getId());
             return  new ResponseEntity<>(res,HttpStatus.ACCEPTED);

             }

            AuthResponse res = new AuthResponse();
            res.setJwt(jwt);
            res.setStatus(true);
            res.setMessage("Login Success");
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }

    private Authentication authenticate(String password, String email) throws Exception {
      UserDetails userDetails = customerUserDetailsServuce.loadUserByUsername(email);
       if (userDetails==null){
           throw new Exception("User not found");
       }


        if (!password.equals(userDetails.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities()
        );

    }

    @PostMapping("/auth/two-factor/{otp}")
    public ResponseEntity<AuthResponse> veriySignInOTP(@PathVariable String otp,
                                                 @RequestParam String id) throws Exception {
        Optional<TwoFactorOTP> twoFactorOTP=twoFactorOTPService.findById(id);
       TwoFactorOTP twoFactorOTP1=twoFactorOTP.get();
        if (twoFactorOTPService.verifyTwoFactorOTP(twoFactorOTP1,otp)){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two factor authentication verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactorOTP1.getJwt());
            return new ResponseEntity<>(res,HttpStatus.OK);

        }
        throw new Exception("Invalid OTP");
    }
}
