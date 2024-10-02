package com.Trading_plaform.Trading_platform.controlers;

import com.Trading_plaform.Trading_platform.Request.ForgotPasswordTokenRequest;
import com.Trading_plaform.Trading_platform.Request.ResetPasswordRequest;
import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.ForgotPasswordToken;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.models.VerificationCode;
import com.Trading_plaform.Trading_platform.response.ApiResponse;
import com.Trading_plaform.Trading_platform.response.AuthResponse;
import com.Trading_plaform.Trading_platform.services.EmailService;
import com.Trading_plaform.Trading_platform.services.ForgotPasswordService;
import com.Trading_plaform.Trading_platform.services.UserService;
import com.Trading_plaform.Trading_platform.services.VerificationCodeService;
import com.Trading_plaform.Trading_platform.utils.OtPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationCodeService verificationService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;



    @GetMapping("api/users/profile")
    public ResponseEntity<User> getUserByProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        if (user!=null) {
            throw new Exception("User not found");
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(
            @RequestHeader("Authorization") String jwt,
             @PathVariable VerificationType verificationType) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        if (user != null) {
            throw new Exception("User not found");
        }
        VerificationCode verificationCode = verificationService.getVerificationCodeByUserId(user.getId());

        if (verificationCode == null) {
            verificationCode = verificationService.sendVerificationCode(user, verificationType);


        }
        if (verificationType.equals(VerificationType.EMAIL)) {
            emailService.sendVerificationOTPEmail(user.getEmail(), verificationCode.getOtp());
        }

            return new ResponseEntity<>("VERIFICATION OTP SENT", HttpStatus.OK);

    }

    @PatchMapping ("api/users/enableTwoFactor/verify-otp/{otp}")
    public ResponseEntity<User> enableTwoFactorAuthentication(
            @PathVariable String otp,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        VerificationCode verificationCode=verificationService.getVerificationCodeByUserId(user.getId());
        String sendTo=verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
                verificationCode.getEmail():verificationCode.getMobile();
        boolean isVerified=verificationCode.getOtp().equals(otp);
        if (isVerified){
            User updatedUser=userService.enableTwoFactorAuthentication(VerificationType.valueOf(verificationCode.getVerificationType()),sendTo,user);
            verificationService.deleteVerificatgionCodeById(verificationCode);
            return  new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        throw new Exception("Wrong otp");

    }

    @PostMapping("auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendForgotPasswordOtp(

            @RequestBody ForgotPasswordTokenRequest request) throws Exception {
        User user=userService.findUserByEmail(request.getSendTo());
        String otp= OtPUtils.generateOTP();
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString();

        ForgotPasswordToken token=forgotPasswordService.findUser(user.getId());

        if (token==null){
            token=forgotPasswordService.createToken(user,id, otp,request.getVerificationType(), request.getSendTo());
        }
        if (request.getVerificationType().equals(VerificationType.EMAIL)){

            emailService.sendVerificationOTPEmail(user.getEmail(),token.getOtp());
        }

        AuthResponse response=new AuthResponse();
        response.setSession(token.getId());
        response.setMessage("password reset otp sent successfully");


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping ("auth/users/reset-password/verify-otp")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String id,
           @RequestBody ResetPasswordRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        ForgotPasswordToken forgotPasswordToken=forgotPasswordService.findById(id);

        boolean isVerified=forgotPasswordToken.getOtp().equals(request.getOtp());

        if (isVerified){
            userService.updatePassword(forgotPasswordToken.getUser(),request.getPassword());

            ApiResponse response =new ApiResponse();
            response.setMessage("password updated succesfully");

            return  new ResponseEntity<>(response,HttpStatus.OK);
        }


        throw new Exception("Wrong otp");

    }



}
