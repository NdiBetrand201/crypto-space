package com.Trading_plaform.Trading_platform.services;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.ForgotPasswordToken;
import com.Trading_plaform.Trading_platform.models.User;

public interface ForgotPasswordService {
    ForgotPasswordToken createToken(User user,
                                    String id,
                                    String otp,
                                    VerificationType verificationType,
                                    String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findUser(Long userId);

    void deleteToken(ForgotPasswordToken token);

}
