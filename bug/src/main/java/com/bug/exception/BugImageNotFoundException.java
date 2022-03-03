package com.bug.exception;

public class BugImageNotFoundException extends RuntimeException {

    public BugImageNotFoundException() {
    }

    public BugImageNotFoundException(String message) {
        super(message);
    }

    public BugImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BugImageNotFoundException(Throwable cause) {
        super(cause);
    }

    public BugImageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
