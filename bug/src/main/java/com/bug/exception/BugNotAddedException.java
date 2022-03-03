package com.bug.exception;

public class BugNotAddedException extends RuntimeException {

    public BugNotAddedException() {
    }

    public BugNotAddedException(String message) {
        super(message);
    }

    public BugNotAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BugNotAddedException(Throwable cause) {
        super(cause);
    }

    public BugNotAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
