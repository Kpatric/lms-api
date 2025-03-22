package com.lms.api.loan.service;


import java.util.List;

public interface CbsTransactionClient {
    List<com.lms.generated.TransactionData> fetchTransactions(String customerNumber);
}


