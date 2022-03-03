package com.comments.exception;

import com.comments.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(Constant.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BugNotFoundException.class)
    public ResponseEntity<Object> handleBugNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(Constant.BUG_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
