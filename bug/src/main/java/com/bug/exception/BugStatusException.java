package com.bug.exception;

public class BugStatusException extends RuntimeException {

    public BugStatusException() {
    }

    public BugStatusException(String message) {
        super(message);
    }

    public BugStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public BugStatusException(Throwable cause) {
        super(cause);
    }

    public BugStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
