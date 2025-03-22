package com.lms.api.loan.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoringResult {
    private Long id;
    private String customerNumber;
    private Integer score;
    private Double limitAmount;
    private String exclusion;
    private String exclusionReason;
}