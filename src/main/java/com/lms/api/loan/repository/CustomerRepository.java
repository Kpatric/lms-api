package com.lms.api.loan.repository;

import com.lms.api.loan.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByCustomerNumber(String customerNumber);
}

