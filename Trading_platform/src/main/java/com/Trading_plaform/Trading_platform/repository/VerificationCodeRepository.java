package com.Trading_plaform.Trading_platform.repository;

import com.Trading_plaform.Trading_platform.models.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    public  VerificationCode findByUserId(Long userId);
}
