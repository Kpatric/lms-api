package com.lms.api.loan.service;

import com.lms.api.loan.dto.CustomerInfoDTO;

public interface KycService {
    CustomerInfoDTO fetchCustomerInfo(String customerNumber);
}
