package com.lms.api.loan.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerInfoDTO {
    private String customerNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationalId;
    private String email;
    private String phoneNumber;
}
