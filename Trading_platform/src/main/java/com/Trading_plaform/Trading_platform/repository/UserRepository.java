package com.Trading_plaform.Trading_platform.repository;

import com.Trading_plaform.Trading_platform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
