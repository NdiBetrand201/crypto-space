package com.Trading_plaform.Trading_platform.implementations;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.ForgotPasswordToken;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.repository.ForgotPasswordRepository;
import com.Trading_plaform.Trading_platform.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgotPasswordImpl implements ForgotPasswordService {
    @Autowired
    private ForgotPasswordRepository forgotpasswordRepository;

    @Override
    public ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo) {
       ForgotPasswordToken token= new ForgotPasswordToken();
       token.setUser(user);
       token.setSendTo(sendTo);
       token.setOtp(otp);
       token.setVerificationType(verificationType);
       token.setId(id);

      ForgotPasswordToken savedToken= forgotpasswordRepository.save(token);
        return savedToken;
    }

    @Override
    public ForgotPasswordToken findById(String id) {
        Optional<ForgotPasswordToken> token= forgotpasswordRepository.findById(id);

        return token.orElse(null);
    }

    @Override
    public ForgotPasswordToken findUser(Long userId) {
        return forgotpasswordRepository.findByUserId(userId);
    }

    @Override
    public void deleteToken(ForgotPasswordToken token) {
     forgotpasswordRepository.delete(token);
    }
}
