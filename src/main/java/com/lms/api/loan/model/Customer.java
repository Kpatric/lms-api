package com.lms.api.loan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String customerNumber;

    private boolean hasActiveLoan;

    public boolean hasActiveLoan() {
        return hasActiveLoan;
    }

    public void activateLoan() {
        this.hasActiveLoan = true;
    }

    public void deactivateLoan() {
        this.hasActiveLoan = false;
    }
}

