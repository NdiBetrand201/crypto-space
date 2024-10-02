package com.Trading_plaform.Trading_platform.Request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String otp;
    private String password;
}
