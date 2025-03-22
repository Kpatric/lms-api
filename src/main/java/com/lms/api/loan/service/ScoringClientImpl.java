package com.lms.api.loan.service;

import com.lms.api.core.config.global.RestClient;
import com.lms.api.loan.dto.ScoringResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoringClientImpl implements ScoringClient {

    @Value("${scoring.base-url}")
    private String baseScoringUrl;

    @Value("${scoring.scoring-url}")
    private String scoringUrl;

    @Value("${scoring.query-score}")
    private String queryScore;

    private final RestClient restClient;


    @Override
    public String initiateScoreQuery(String customerNumber) {
        String url = baseScoringUrl + scoringUrl + customerNumber;
        return restClient.restTemplate().getForObject(url, String.class);
    }

    @Override
    @Retryable(
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000, multiplier = 2),
            include = {IllegalStateException.class}
    )
    public ScoringResult pollScore(String token) {
        String url = baseScoringUrl + queryScore + token;
        var response = restClient.restTemplate().getForEntity(url, ScoringResult.class);

        if (response.getBody() == null || response.getBody().getScore() == null) {
            log.info("Scoring not ready, retrying...");
            throw new IllegalStateException("Score not ready");
        }

        return response.getBody();
    }
}
