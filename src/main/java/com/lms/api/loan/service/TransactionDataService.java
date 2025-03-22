package com.lms.api.loan.service;

import com.lms.api.loan.dto.TransactionDataDTO;

import java.util.List;

public interface TransactionDataService {
    List<TransactionDataDTO> getTransactionData(String customerNumber);
}

