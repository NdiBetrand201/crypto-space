package com.Trading_plaform.Trading_platform.repository;

import com.Trading_plaform.Trading_platform.models.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin,String> {
}
