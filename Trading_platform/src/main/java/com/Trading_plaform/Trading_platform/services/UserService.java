package com.Trading_plaform.Trading_platform.services;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
 public User findUserProfileByJwt(String jwt) throws Exception;

 public  User findUserByEmail(String email) throws Exception;

 public User findUserById(Long userId) throws Exception;

 public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo,User user);

 User updatePassword(User user,String newPassword);



}
