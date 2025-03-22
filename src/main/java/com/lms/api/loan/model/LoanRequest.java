package com.lms.api.loan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "loan_transactions")
public class LoanRequest extends AuditorEntity{

    private String customerNumber;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private Integer score;

    private Double limitAmount;

}

