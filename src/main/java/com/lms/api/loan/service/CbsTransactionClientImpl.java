package com.lms.api.loan.service;

import com.lms.api.core.config.global.SoapConfig;
import com.lms.api.core.exception.KycServiceException;
import com.lms.generated.TransactionData;
import com.lms.generated.TransactionsRequest;
import com.lms.generated.TransactionsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceTransportException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CbsTransactionClientImpl implements CbsTransactionClient {

    private final SoapConfig soapConfig;

    @Override
    public List<TransactionData> fetchTransactions(String customerNumber) {
        TransactionsRequest request = new TransactionsRequest();
        request.setCustomerNumber(customerNumber);

        try {
            TransactionsResponse response = (TransactionsResponse)
                    soapConfig.transactionWebServiceTemplate().marshalSendAndReceive(request);

            return response.getTransactions();
        } catch (WebServiceTransportException ex) {
            log.error("SOAP transport error while fetching transaction info for {}: {}", customerNumber, ex.getMessage());
            throw new KycServiceException("Error fetching transaction info from KYC SOAP Service", ex);
        } catch (Exception ex) {
            log.error("General error while fetching transaction info for {}: {}", customerNumber, ex.getMessage());
            throw new KycServiceException("Unexpected error fetching transaction info", ex);
        }

    }
}


