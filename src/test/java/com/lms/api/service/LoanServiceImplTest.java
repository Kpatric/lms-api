package com.lms.api.service;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoanServiceImplTest {

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
    void shouldApproveLoanWhenEligible() {
        var customer = new Customer("123", false);
        var scoring = ScoringResult.builder()
                .score(600)
                .limitAmount(10000.0)
                .exclusion("No Exclusion")
                .exclusionReason("None")
                .build();

        when(customerRepo.findById("123")).thenReturn(Optional.of(customer));
        when(scoringClient.initiateScoreQuery("123")).thenReturn("token-123");
        when(scoringClient.pollScore("token-123")).thenReturn(scoring);

        service.requestLoan("123", 5000.0);

        verify(customerRepo).save(any(Customer.class));
        verify(loanRepo, times(2)).save(any(LoanRequest.class)); // initial + update
    }

    @Test
    void shouldFailLoanWhenLimitIsLower() {
        var customer = new Customer("123", false);
        var scoring = ScoringResult.builder()
                .score(600)
                .limitAmount(10000.0)
                .exclusion("No Exclusion")
                .exclusionReason("None")
                .build();

        when(customerRepo.findById("123")).thenReturn(Optional.of(customer));
        when(scoringClient.initiateScoreQuery("123")).thenReturn("token-456");
        when(scoringClient.pollScore("token-456")).thenReturn(scoring);

        service.requestLoan("123", 10000.0);

        verify(loanRepo, times(2)).save(any(LoanRequest.class));
    }

    @Test
    void shouldThrowIfCustomerNotFound() {
        when(customerRepo.findById("999")).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.requestLoan("999", 1000.0)
        );
    }

    @Test
    void shouldThrowIfCustomerHasActiveLoan() {
        var customer = new Customer("123", true);
        when(customerRepo.findById("123")).thenReturn(Optional.of(customer));

        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () ->
                service.requestLoan("123", 1000.0)
        );
    }
}
