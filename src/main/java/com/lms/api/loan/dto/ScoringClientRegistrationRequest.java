package com.lms.api.loan.dto;

import lombok.Data;

@Data
public class ScoringClientRegistrationRequest {
    private String url;
    private String name;
    private String username;
    private String password;
}
