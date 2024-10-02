package com.Trading_plaform.Trading_platform.repository;

import com.Trading_plaform.Trading_platform.models.TwoFactorOTP;
import com.Trading_plaform.Trading_platform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TwoFactorOTPRepository extends JpaRepository<TwoFactorOTP,String> {




    TwoFactorOTP findByUserId(Long userId);
}
