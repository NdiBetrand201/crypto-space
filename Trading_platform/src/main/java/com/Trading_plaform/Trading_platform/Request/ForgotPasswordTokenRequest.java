package com.Trading_plaform.Trading_platform.Request;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {
    private String sendTo;
    private VerificationType verificationType;
}
