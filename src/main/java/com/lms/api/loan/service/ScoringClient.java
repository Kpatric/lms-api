package com.lms.api.loan.service;

import com.lms.api.loan.dto.ScoringResult;

public interface ScoringClient {
    String initiateScoreQuery(String customerNumber);
    ScoringResult pollScore(String token);
}
