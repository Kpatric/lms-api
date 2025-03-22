package com.lms.api.loan.controller;

import com.lms.api.loan.service.ScoringRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scoring")
public class ScoringRegistrationController {

    private final ScoringRegistrationService registrationService;

    @Operation(summary = "Register LMS Transaction endpoint with Scoring Engine")
    @PostMapping("/register-endpoint")
    public ResponseEntity<String> registerEndpoint() {
        String token = registrationService.registerEndpoint();
        return ResponseEntity.ok("✅ Endpoint registered successfully. Token: " + token);
    }
}
