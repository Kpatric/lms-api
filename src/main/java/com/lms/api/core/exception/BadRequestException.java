package com.lms.api.core.exception;

/**
 * Created by Patrick Muriithi.
 * Date: 5/25/2024
 * Time: 12:25 PM
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
