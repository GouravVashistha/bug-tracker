package com.bug.exception;

public class ImageNotAddedException extends RuntimeException {

    public ImageNotAddedException() {
    }

    public ImageNotAddedException(String message) {
        super(message);
    }

    public ImageNotAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageNotAddedException(Throwable cause) {
        super(cause);
    }

    public ImageNotAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
