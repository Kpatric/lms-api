package com.lms.api.loan.dto;

import lombok.Data;

@Data
public class ScoringClientRegistrationResponse {
    private int id;
    private String url;
    private String name;
    private String username;
    private String password;
    private String token;
}
