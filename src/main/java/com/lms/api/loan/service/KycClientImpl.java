package com.lms.api.loan.service;

import com.lms.api.core.config.global.SoapConfig;
import com.lms.api.core.exception.KycServiceException;
import com.lms.generated.Customer;
import com.lms.generated.CustomerRequest;
import com.lms.generated.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceTransportException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KycClientImpl implements KycClient {

    private final SoapConfig wsTemplate;

    @Override
    public Customer getCustomerInfo(String customerNumber) {
        CustomerRequest request = new CustomerRequest();
        request.setCustomerNumber(customerNumber);

        try {
            CustomerResponse response = (CustomerResponse)
                    wsTemplate.customerWebServiceTemplate().marshalSendAndReceive(request);

            return response.getCustomer();
        } catch (WebServiceTransportException ex) {
            log.error("SOAP transport error while fetching customer info for {}: {}", customerNumber, ex.getMessage());
            throw new KycServiceException("Error fetching customer info from KYC SOAP Service", ex);
        } catch (Exception ex) {
            log.error("General error while fetching customer info for {}: {}", customerNumber, ex.getMessage());
            throw new KycServiceException("Unexpected error fetching customer info", ex);
        }
    }
}
