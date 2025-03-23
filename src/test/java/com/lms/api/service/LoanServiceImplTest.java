package com.lms.api.service;

import com.lms.api.core.exception.BadRequestException;
import com.lms.api.loan.dto.ScoringResult;
import com.lms.api.loan.model.Customer;
import com.lms.api.loan.model.LoanRequest;
import com.lms.api.loan.repository.CustomerRepository;
import com.lms.api.loan.repository.LoanRequestRepository;
import com.lms.api.loan.service.LoanServiceImpl;
import com.lms.api.loan.service.ScoringClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanServiceImplTest {

    private CustomerRepository customerRepo;
    private LoanRequestRepository loanRepo;
    private ScoringClient scoringClient;
    private LoanServiceImpl service;

    @BeforeEach
    void setUp() {
        customerRepo = mock(CustomerRepository.class);
        loanRepo = mock(LoanRequestRepository.class);
        scoringClient = mock(ScoringClient.class);
        service = new LoanServiceImpl(customerRepo, loanRepo, scoringClient);
    }

    @Test
    void shouldApproveLoan_WhenCustomerEligibleAndLimitIsSufficient() {
        String customerNumber = "123";
        Customer customer = new Customer(customerNumber, false);
        ScoringResult scoring = ScoringResult.builder()
                .score(700)
                .limitAmount(10000.0)
                .exclusion("No Exclusion")
                .exclusionReason("None")
                .build();

        when(customerRepo.findById(customerNumber)).thenReturn(Optional.of(customer));
        when(scoringClient.initiateScoreQuery(customerNumber)).thenReturn("token-123");
        when(scoringClient.pollScore("token-123")).thenReturn(scoring);

        service.requestLoan(customerNumber, 5000.0);

        verify(customerRepo).save(any(Customer.class)); // customer loan status updated
        verify(loanRepo, times(2)).save(any(LoanRequest.class)); // initial + update
    }

    @Test
    void shouldRejectLoan_WhenRequestedAmountEqualsLimit() {
        String customerNumber = "123";
        Customer customer = new Customer(customerNumber, false);
        ScoringResult scoring = ScoringResult.builder()
                .score(600)
                .limitAmount(10000.0)
                .exclusion("No Exclusion")
                .exclusionReason("None")
                .build();

        when(customerRepo.findById(customerNumber)).thenReturn(Optional.of(customer));
        when(scoringClient.initiateScoreQuery(customerNumber)).thenReturn("token-456");
        when(scoringClient.pollScore("token-456")).thenReturn(scoring);

        service.requestLoan(customerNumber, 10000.0);

        verify(loanRepo, times(2)).save(any(LoanRequest.class));
    }

    @Test
    void shouldThrowException_WhenCustomerDoesNotExist() {
        String customerNumber = "999";
        when(customerRepo.findById(customerNumber)).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () ->
                service.requestLoan(customerNumber, 1000.0)
        );

        assertEquals("Customer not found: "+customerNumber+"", exception.getMessage());
        verify(loanRepo, never()).save(any());
    }

    @Test
    void shouldThrowException_WhenCustomerHasActiveLoan() {
        String customerNumber = "123";
        Customer customer = new Customer(customerNumber, true);

        when(customerRepo.findById(customerNumber)).thenReturn(Optional.of(customer));

        BadRequestException exception = assertThrows(BadRequestException.class, () ->
                service.requestLoan(customerNumber, 1000.0)
        );

        assertEquals("Customer has an active loan", exception.getMessage());
        verify(loanRepo, never()).save(any());
        verify(customerRepo, never()).save(any());
    }

    @Test
    void shouldMarkCustomerAsLoanActive_WhenLoanApproved() {
        String customerNumber = "999";
        Customer customer = new Customer(customerNumber, false);
        ScoringResult scoring = ScoringResult.builder()
                .score(750)
                .limitAmount(15000.0)
                .exclusion("No Exclusion")
                .exclusionReason("None")
                .build();

        when(customerRepo.findById(customerNumber)).thenReturn(Optional.of(customer));
        when(scoringClient.initiateScoreQuery(customerNumber)).thenReturn("token-999");
        when(scoringClient.pollScore("token-999")).thenReturn(scoring);

        service.requestLoan(customerNumber, 10000.0);

        assertTrue(customer.hasActiveLoan());
        verify(customerRepo).save(customer);
    }
}
