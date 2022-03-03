package com.bugtracker.user.exception;

public class DomainIncorrectException extends RuntimeException {

    public DomainIncorrectException() {
    }

    public DomainIncorrectException(String message) {
        super(message);
    }

    public DomainIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainIncorrectException(Throwable cause) {
        super(cause);
    }

    public DomainIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
