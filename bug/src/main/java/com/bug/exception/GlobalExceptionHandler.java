package com.bug.exception;

import com.bug.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception) {
        return new ResponseEntity<>(Constant.PROJECT_NOT_EXISTS, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BugNotAddedException.class)
    public ResponseEntity<Object> handleBugNotAddedException(BugNotAddedException exception) {
        return new ResponseEntity<>(Constant.BUG_NOT_ADDED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNotAddedException.class)
    public ResponseEntity<Object> handleImageNotAddedException(ImageNotAddedException exception) {
        return new ResponseEntity<>(Constant.IMAGE_NOT_ADDED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException exception) {
        return new ResponseEntity<>(Constant.CLIENT_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BugNotFoundException.class)
    public ResponseEntity<Object> handleBugNotFoundException(BugNotFoundException exception) {
        return new ResponseEntity<>(Constant.BUG_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BugImageNotFoundException.class)
    public ResponseEntity<Object> handleBugImageNotFoundException(BugImageNotFoundException exception) {
        return new ResponseEntity<>(Constant.IMAGE_OF_BUG_WITH_BUG_ID, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BugStatusException.class)
    public ResponseEntity<Object> handleBugStatusException(BugStatusException exception) {
        return new ResponseEntity<>(Constant.BUG_STATUS_NOT_CORRECT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StaffNotFoundException.class)
    public ResponseEntity<Object> handleBugStatusException(StaffNotFoundException exception) {
        return new ResponseEntity<>(Constant.STAFF_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BugTransferException.class)
    public ResponseEntity<Object> handleBugTransferException(BugTransferException exception) {
        return new ResponseEntity<>(Constant.BUG_NOT_TRANSFERRED, HttpStatus.BAD_REQUEST);
    }
}
