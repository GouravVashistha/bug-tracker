package com.bugtracker.user.exception;

public class PasswordSyntaxIncorrectException extends RuntimeException{

    public PasswordSyntaxIncorrectException() {
    }

    public PasswordSyntaxIncorrectException(String message) {
        super(message);
    }

    public PasswordSyntaxIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordSyntaxIncorrectException(Throwable cause) {
        super(cause);
    }

    public PasswordSyntaxIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
