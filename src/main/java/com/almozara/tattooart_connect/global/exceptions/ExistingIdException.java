package com.almozara.tattooart_connect.global.exceptions;

public class ExistingIdException extends RuntimeException {
    public ExistingIdException(String message) {
        super(message);
    }
}
