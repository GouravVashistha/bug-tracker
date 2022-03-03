package com.bugtracker.user.exception;

import com.bugtracker.user.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ForbiddenAuthorityException.class)
    public ResponseEntity<Object> handleForbiddenAuthorityException(ForbiddenAuthorityException exception) {
        return new ResponseEntity<>(Constant.FORBIDDEN_REQUEST, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException exception) {
        return new ResponseEntity<>(Constant.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<Object> handleEmailAlreadyTakenException(EmailAlreadyTakenException exception) {
        return new ResponseEntity<>(Constant.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactAlreadyExistsException.class)
    public ResponseEntity<Object> handleContactAlreadyExistsException(ContactAlreadyExistsException exception) {
        return new ResponseEntity<>(Constant.CONTACT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(Constant.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DomainIncorrectException.class)
    public ResponseEntity<Object> handleDomainIncorrectException(DomainIncorrectException exception) {
        return new ResponseEntity<>(Constant.DOMAIN_NOT_AUTHORIZED, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(IncorrectCredentials.class)
    public ResponseEntity<Object> handleIncorrectCredentials(IncorrectCredentials exception) {
        return new ResponseEntity<>(Constant.INCORRECT_CREDENTIALS, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(PasswordSyntaxIncorrectException.class)
    public ResponseEntity<Object> handlePasswordSyntaxIncorrectException(PasswordSyntaxIncorrectException exception) {
        return new ResponseEntity<>(Constant.PASSWORD_SYNTAX_INCORRECT, HttpStatus.BAD_REQUEST);
    }
}
