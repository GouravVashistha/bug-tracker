package com.bugtracker.user.exception;

public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException() {
    }

    public ContactAlreadyExistsException(String message) {
        super(message);
    }

    public ContactAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ContactAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
