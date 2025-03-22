package com.lms.api.loan.service;

import com.lms.api.loan.dto.CustomerInfoDTO;
import com.lms.api.loan.util.KycMapper;
import com.lms.generated.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KycServiceImpl implements KycService {

    private final KycClient kycClient;
    private final KycMapper kycMapper;

    @Override
    public CustomerInfoDTO fetchCustomerInfo(String customerNumber) {
        Customer info = kycClient.getCustomerInfo(customerNumber);
        return kycMapper.toDto(info);
    }
}

