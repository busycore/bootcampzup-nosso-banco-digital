package com.zupchallenge.SpringBootChallenge.shared.errors.exceptions;

public class IncompleteStepsException extends RuntimeException {
    public IncompleteStepsException(String message) {
        super(message);
    }
}
