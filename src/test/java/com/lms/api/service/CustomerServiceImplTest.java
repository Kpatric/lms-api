package com.lms.api.service;

import com.lms.api.core.exception.CustomerAlreadyExistsException;
import com.lms.api.loan.dto.CustomerInfoDTO;
import com.lms.api.loan.model.Customer;
import com.lms.api.loan.repository.CustomerRepository;
import com.lms.api.loan.service.CustomerServiceImpl;
import com.lms.api.loan.service.KycService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    private CustomerRepository repository;
    private KycService kycService;
    private CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        kycService = mock(KycService.class);
        service = new CustomerServiceImpl(repository, kycService);
    }

    @Test
    void shouldSubscribeNewCustomer() {
        // given
        String customerNumber = "123456";
        when(repository.existsByCustomerNumber(customerNumber)).thenReturn(false);
        when(kycService.fetchCustomerInfo(customerNumber)).thenReturn(new CustomerInfoDTO());

        // when
        service.subscribeCustomer(customerNumber);

        // then
        verify(kycService).fetchCustomerInfo(customerNumber);
        verify(repository).save(any(Customer.class));
    }

    @Test
    void shouldThrowWhenCustomerAlreadyExists() {
        // given
        String customerNumber = "123456";
        when(repository.existsByCustomerNumber(customerNumber)).thenReturn(true);

        // then
        assertThrows(CustomerAlreadyExistsException.class,
                () -> service.subscribeCustomer(customerNumber));

        // and
        verify(repository, never()).save(any());
        verify(kycService, never()).fetchCustomerInfo(any());
    }
}
