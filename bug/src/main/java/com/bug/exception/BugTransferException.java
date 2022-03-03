package com.bug.exception;

public class BugTransferException extends RuntimeException {

    public BugTransferException() {
    }

    public BugTransferException(String message) {
        super(message);
    }

    public BugTransferException(String message, Throwable cause) {
        super(message, cause);
    }

    public BugTransferException(Throwable cause) {
        super(cause);
    }

    public BugTransferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
