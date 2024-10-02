package com.Trading_plaform.Trading_platform.implementations;

import com.Trading_plaform.Trading_platform.models.TwoFactorOTP;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.repository.TwoFactorOTPRepository;
import com.Trading_plaform.Trading_platform.repository.UserRepository;
import com.Trading_plaform.Trading_platform.services.TwoFactorOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TwoFactorOTPServiceImpl implements TwoFactorOTPService {


    @Autowired
    private TwoFactorOTPRepository twoFactorOTPRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt) throws Exception {
        UUID uuid = UUID.randomUUID();
        String id=uuid.toString();

        TwoFactorOTP twoFactorOTP = new TwoFactorOTP();
        twoFactorOTP.setOtp(otp);
        twoFactorOTP.setJwt(jwt);
        twoFactorOTP.setId(id);
        twoFactorOTP.setUser(user);

        return twoFactorOTPRepository.save(twoFactorOTP);

    }

    @Override
    public TwoFactorOTP findByUser(Long userId) throws Exception {


        return twoFactorOTPRepository.findByUserId(userId);
    }

    @Override
    public Optional<TwoFactorOTP> findById(String id) throws Exception {
        return twoFactorOTPRepository.findById(id);
    }

    @Override
    public boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP, String otp) throws Exception {
        return twoFactorOTP.getOtp().equals(otp);
    }

    @Override
    public void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP) throws Exception {
     twoFactorOTPRepository.delete(twoFactorOTP);
    }
}
