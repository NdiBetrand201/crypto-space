package com.Trading_plaform.Trading_platform.models;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.foreign.MemorySegment;
import java.time.LocalDate;

@Data
@Entity
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private  Wallet wallet;

    private WalletTransaction type;

    private LocalDate date;

    private String purpose;

    private Long amount;

}
