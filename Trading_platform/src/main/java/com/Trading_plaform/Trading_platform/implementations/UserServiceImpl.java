package com.Trading_plaform.Trading_platform.implementations;

import com.Trading_plaform.Trading_platform.config.JwtProvider;
import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.TwoFactorAuth;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.repository.UserRepository;
import com.Trading_plaform.Trading_platform.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository  userRepository;


    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromJwtToke(jwt);
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("Could not find user ");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("Could not find user ");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.get() == null){
            throw new Exception("Could not find user ");
        }
        return user.get();
    }

    @Override
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
        TwoFactorAuth twoFactorAuth=new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setSendTo(verificationType);

        user.setTwoFactorAuth(twoFactorAuth);
        return userRepository.save(user);
    }


    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);

        return userRepository.save(user);
    }
}
