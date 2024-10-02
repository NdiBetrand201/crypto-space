package com.Trading_plaform.Trading_platform.models;

import com.Trading_plaform.Trading_platform.domain.VerificationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class TwoFactorAuth {

private  boolean isEnabled=false;
private VerificationType sendTo;


}
