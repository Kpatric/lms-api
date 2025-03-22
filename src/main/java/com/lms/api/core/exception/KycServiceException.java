package com.lms.api.core.exception;

public class KycServiceException extends RuntimeException {
    public KycServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

