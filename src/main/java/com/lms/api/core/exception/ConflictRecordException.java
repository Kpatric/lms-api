package com.lms.api.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictRecordException extends Exception {
    public ConflictRecordException(String message) {
        super(message);
    }

    public ConflictRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
