package com.bugtracker.user.exception;

public class ForbiddenAuthorityException extends RuntimeException{

    public ForbiddenAuthorityException() {
    }

    public ForbiddenAuthorityException(String message) {
        super(message);
    }

    public ForbiddenAuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenAuthorityException(Throwable cause) {
        super(cause);
    }

    public ForbiddenAuthorityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
