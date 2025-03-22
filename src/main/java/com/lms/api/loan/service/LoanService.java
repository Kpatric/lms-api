package com.lms.api.loan.service;

import com.lms.api.loan.dto.LoanStatusResponse;
import com.lms.api.loan.model.LoanRequest;

public interface LoanService {
    LoanRequest requestLoan(String customerNumber, Double amount);
    LoanStatusResponse getLoanStatus(String customerNumber);
}

