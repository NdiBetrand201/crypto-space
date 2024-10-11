package com.Trading_plaform.Trading_platform.repository;

import com.Trading_plaform.Trading_platform.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order  findByUserId(Long userId);
}
