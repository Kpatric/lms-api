package com.lms.api.loan.service;

import com.lms.api.loan.dto.TransactionDataDTO;
import com.lms.api.loan.util.TransactionDataMapper;
import com.lms.generated.TransactionData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDataServiceImpl implements TransactionDataService {

    private final CbsTransactionClient cbsClient;
    private final TransactionDataMapper mapper;

    public TransactionDataServiceImpl(CbsTransactionClient cbsClient,
                                      TransactionDataMapper mapper) {
        this.cbsClient = cbsClient;
        this.mapper = mapper;
    }

    @Override
    public List<TransactionDataDTO> getTransactionData(String customerNumber) {
        List<TransactionData> transactions = cbsClient.fetchTransactions(customerNumber);

        return transactions.stream()
                .map(mapper::toDto)
                .toList();
    }
}
