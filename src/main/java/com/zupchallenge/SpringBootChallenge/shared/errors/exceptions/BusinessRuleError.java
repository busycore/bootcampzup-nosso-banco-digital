package com.zupchallenge.SpringBootChallenge.shared.errors.exceptions;

public class BusinessRuleError extends RuntimeException {
    public BusinessRuleError(String message) {
        super(message);
    }
}
