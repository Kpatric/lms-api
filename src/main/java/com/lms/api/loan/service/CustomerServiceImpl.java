package com.lms.api.loan.service;

import com.lms.api.core.exception.BadRequestException;
import com.lms.api.core.exception.CustomerAlreadyExistsException;
import com.lms.api.loan.dto.CustomerInfoDTO;
import com.lms.api.loan.model.Customer;
import com.lms.api.loan.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final KycService kycService;

    @Override
    public Customer subscribeCustomer(String customerNumber) {

        if (repository.existsByCustomerNumber(customerNumber)) {
            throw new CustomerAlreadyExistsException(customerNumber);
        }

        CustomerInfoDTO customer = null;
        try {
            customer = kycService.fetchCustomerInfo(customerNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching customer info", e);
        }

        if (customer == null) {
            throw new BadRequestException("Customer not found in CBS");
        }

        Customer newCustomer = new Customer(customerNumber, true);
        return repository.save(newCustomer);
    }
}
