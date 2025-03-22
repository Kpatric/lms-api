package com.lms.api.loan.dto;

import com.lms.api.loan.model.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanStatusResponse {
    private LoanStatus status;
    private Integer score;
    private Double limitAmount;
    private String message;
}