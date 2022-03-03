package com.bugtracker.role.exception;

import com.bugtracker.role.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleAdminNotRegisteredException(RoleNotFoundException exception) {
        return new ResponseEntity<>(Constant.ROLE_NOT_EXISTS, HttpStatus.BAD_REQUEST);
    }
}
