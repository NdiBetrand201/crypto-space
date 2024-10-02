package com.Trading_plaform.Trading_platform.models;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @OneToOne
    private  User user;

    private  String otp;

    private String sendTo;

    private VerificationType verificationType;
}
