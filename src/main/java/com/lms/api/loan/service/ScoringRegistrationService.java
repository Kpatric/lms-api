package com.lms.api.loan.service;

import com.lms.api.core.config.global.RestClient;
import com.lms.api.core.exception.BadRequestException;
import com.lms.api.loan.dto.ScoringClientRegistrationRequest;
import com.lms.api.loan.dto.ScoringClientRegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoringRegistrationService {

    @Value("${scoring.registration-url}")
    private String registrationUrl;

    @Value("${scoring.callback-url}")
    private String callbackUrl;

    @Value("${scoring.client-name}")
    private String clientName;

    @Value("${scoring.callback-username}")
    private String username;

    @Value("${scoring.callback-password}")
    private String password;

    private final RestClient restClient;

    public String registerEndpoint() {
        ScoringClientRegistrationRequest request = new ScoringClientRegistrationRequest();
        request.setUrl(callbackUrl);
        request.setName(clientName);
        request.setUsername(username);
        request.setPassword(password);

        ResponseEntity<ScoringClientRegistrationResponse> response = restClient.restTemplate().postForEntity(
                registrationUrl,
                request,
                ScoringClientRegistrationResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String token = response.getBody().getToken();
            return token;
        } else {
            log.error("Failed to register with scoring engine: {}", response.getStatusCode());
            throw new BadRequestException("Failed to register with scoring engine");
        }
    }
}

