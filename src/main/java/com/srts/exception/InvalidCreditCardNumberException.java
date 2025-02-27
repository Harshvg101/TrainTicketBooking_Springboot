package com.srts.exception;

public class InvalidCreditCardNumberException extends RuntimeException {
    public InvalidCreditCardNumberException(String message) {
        super(message);
    }
}
