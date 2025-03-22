package com.lms.api.loan.repository;

import com.lms.api.loan.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, UUID> {
    Optional<LoanRequest> findTopByCustomerNumberOrderByDateCreated(String customerNumber);
}
