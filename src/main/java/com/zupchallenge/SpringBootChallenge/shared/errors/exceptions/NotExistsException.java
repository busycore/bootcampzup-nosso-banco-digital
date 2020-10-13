package com.zupchallenge.SpringBootChallenge.shared.errors.exceptions;

public class NotExistsException extends RuntimeException {
    public NotExistsException(String message) {
        super(message);
    }
}
