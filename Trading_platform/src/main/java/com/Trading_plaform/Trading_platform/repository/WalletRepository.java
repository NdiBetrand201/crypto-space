package com.Trading_plaform.Trading_platform.repository;

import com.Trading_plaform.Trading_platform.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findByUserId(Long userId);
}
