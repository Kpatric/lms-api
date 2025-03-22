package com.lms.api.loan.service;

import com.lms.generated.Customer;

public interface KycClient {
    Customer getCustomerInfo(String customerNumber);
}

