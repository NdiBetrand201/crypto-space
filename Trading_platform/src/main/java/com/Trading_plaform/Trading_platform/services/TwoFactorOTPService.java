package com.Trading_plaform.Trading_platform.services;

import com.Trading_plaform.Trading_platform.models.TwoFactorOTP;
import com.Trading_plaform.Trading_platform.models.User;

import java.util.Optional;

public interface TwoFactorOTPService {
    TwoFactorOTP createTwoFactorOTP(User user,String otp,String jwt)throws Exception;

    TwoFactorOTP findByUser(Long userId) throws Exception;

    Optional<TwoFactorOTP> findById(String id)throws Exception;

    boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP,String otp)throws Exception;

    void  deleteTwoFactorOTP(TwoFactorOTP twoFactorOTPq)throws Exception;
}
