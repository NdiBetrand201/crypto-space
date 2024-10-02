package com.Trading_plaform.Trading_platform.implementations;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.models.VerificationCode;
import com.Trading_plaform.Trading_platform.repository.VerificationCodeRepository;
import com.Trading_plaform.Trading_platform.services.VerificationCodeService;
import com.Trading_plaform.Trading_platform.utils.OtPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
   @Autowired
   private VerificationCodeRepository verificationCodeRepository;



    @Override
    public VerificationCode sendVerificationCode(User user, VerificationType verificationType) {
        VerificationCode verificationCode1=new VerificationCode();
        verificationCode1.setOtp(OtPUtils.generateOTP());
        verificationCode1.setVerificationType(String.valueOf(verificationType));
        verificationCode1.setUser(user);
        return verificationCodeRepository.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
        Optional<VerificationCode> verificationCode=verificationCodeRepository.findById(id);
        if(verificationCode.isPresent()){
                return  verificationCode.get();

        }
        throw new Exception("Verification code not found");
    }

    @Override
    public VerificationCode getVerificationCodeByUserId(Long userId) {


        return verificationCodeRepository.findByUserId(userId);
    }

    @Override
    public void deleteVerificatgionCodeById(VerificationCode verificationCode) {

    verificationCodeRepository.delete(verificationCode);
    }
}
