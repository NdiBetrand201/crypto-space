package com.Trading_plaform.Trading_platform.services;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.models.VerificationCode;

public interface VerificationCodeService {

       VerificationCode sendVerificationCode(User user, VerificationType verificationType);
        VerificationCode getVerificationCodeById(Long id) throws Exception;
       VerificationCode getVerificationCodeByUserId(Long userId);

       void deleteVerificatgionCodeById(    VerificationCode verificationCode);
}
