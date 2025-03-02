package com.srts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCreditCardNumberException.class)
    public ResponseEntity<String> handleInvalidCardException(InvalidCreditCardNumberException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<String> handleNoRecordFound(NoRecordFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidFileContentException.class)
    public ResponseEntity<String> handleInvalidFileException(InvalidFileContentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file format: " + ex.getMessage());
    }
}
