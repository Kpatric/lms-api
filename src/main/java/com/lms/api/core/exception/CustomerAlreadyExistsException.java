package com.lms.api.core.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String customerNumber) {
        super("Customer with number " + customerNumber + " already exists");
    }
}

