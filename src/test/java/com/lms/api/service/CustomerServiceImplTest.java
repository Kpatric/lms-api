package com.lms.api.service;

import com.lms.api.core.exception.CustomerAlreadyExistsException;
import com.lms.api.loan.model.Customer;
import com.lms.api.loan.repository.CustomerRepository;
import com.lms.api.loan.service.CustomerServiceImpl;
import com.lms.api.loan.service.KycService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    private CustomerRepository repository;
    private CustomerServiceImpl service;
    private KycService kycService;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        service = new CustomerServiceImpl(repository, kycService);
    }

    @Test
    void shouldSubscribeNewCustomer() {
        String customerNumber = "123456";
        when(repository.existsByCustomerNumber(customerNumber)).thenReturn(false);

        service.subscribeCustomer(customerNumber);

        verify(repository).save(any(Customer.class));
    }

    @Test
    void shouldThrowWhenCustomerAlreadyExists() {
        String customerNumber = "123456";
        when(repository.existsByCustomerNumber(customerNumber)).thenReturn(true);

        assertThrows(CustomerAlreadyExistsException.class,
                () -> service.subscribeCustomer(customerNumber));
    }
}
