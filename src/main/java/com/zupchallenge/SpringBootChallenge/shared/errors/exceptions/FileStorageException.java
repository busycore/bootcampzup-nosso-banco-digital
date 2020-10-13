package com.zupchallenge.SpringBootChallenge.shared.errors.exceptions;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }
}
