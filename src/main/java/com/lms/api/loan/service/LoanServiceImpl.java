package com.lms.api.loan.service;

import com.lms.api.core.exception.BadRequestException;
import com.lms.api.loan.dto.LoanStatusResponse;
import com.lms.api.loan.dto.ScoringResult;
import com.lms.api.loan.model.Customer;
import com.lms.api.loan.model.LoanRequest;
import com.lms.api.loan.model.LoanStatus;
import com.lms.api.loan.repository.CustomerRepository;
import com.lms.api.loan.repository.LoanRequestRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final CustomerRepository customerRepo;
    private final LoanRequestRepository loanRepo;
    private final ScoringClient scoringClient;

    @Override
    @Transactional
    public LoanRequest requestLoan(String customerNumber, Double amount) {
        Customer customer = customerRepo.findById(customerNumber)
                .orElseThrow(() -> new BadRequestException("Customer not found: " + customerNumber));

        if (customer.hasActiveLoan()) {
            throw new BadRequestException("Customer has an active loan");
        }

        LoanRequest loanRequest = LoanRequest.builder()
                .customerNumber(customerNumber)
                .amount(amount)
                .status(LoanStatus.PENDING)
                .build();

        loanRepo.save(loanRequest);

        String token = scoringClient.initiateScoreQuery(customerNumber);

        try {
            ScoringResult scoring = scoringClient.pollScore(token);

            if (scoring.getLimitAmount() >= amount) {
                loanRequest.setStatus(LoanStatus.APPROVED);
                loanRequest.setScore(scoring.getScore());
                loanRequest.setLimitAmount(scoring.getLimitAmount());
                customer.activateLoan();
            } else {
                loanRequest.setStatus(LoanStatus.FAILED);
            }
        } catch (Exception ex) {
            log.error("Scoring failed for customer: {}", customerNumber, ex);
            loanRequest.setStatus(LoanStatus.FAILED);
        }
        customerRepo.save(customer);
        return loanRepo.save(loanRequest);
    }

    @Override
    public LoanStatusResponse getLoanStatus(String customerNumber) {
        LoanRequest latest = loanRepo.findTopByCustomerNumberOrderByDateCreated(customerNumber)
                .orElseThrow(() -> new BadRequestException("No loan found for customer"));

        String message = switch (latest.getStatus()) {
            case APPROVED -> "Loan approved";
            case FAILED -> "Loan rejected or scoring failed";
            case PENDING -> "Loan is being processed";
        };

        return new LoanStatusResponse(
                latest.getStatus(),
                latest.getScore(),
                latest.getLimitAmount(),
                message
        );
    }

}

