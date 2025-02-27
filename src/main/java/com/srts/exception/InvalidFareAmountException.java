package com.srts.exception;

public class InvalidFareAmountException extends RuntimeException {
    public InvalidFareAmountException(String message) {
        super(message);
    }
}
