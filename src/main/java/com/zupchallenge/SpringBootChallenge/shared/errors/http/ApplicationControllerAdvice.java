package com.zupchallenge.SpringBootChallenge.shared.errors.http;


import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        return new APIErrorsList(errors);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleAlreadyExistsException(AlreadyExistsException ex){
        return new APIErrorsList(ex.getMessage());
    }

    @ExceptionHandler(NotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleNotExistsException(NotExistsException ex){
        return new APIErrorsList(ex.getMessage());
    }

    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleFileStorageException(FileStorageException ex){
        return new APIErrorsList(ex.getMessage());
    }

    @ExceptionHandler(IncompleteStepsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public APIErrorsList handleIncompleteStepsException(IncompleteStepsException ex){
        return new APIErrorsList(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return new APIErrorsList("There are errors in JSON Body");
    }

    @ExceptionHandler(BusinessRuleError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleBusinessRuleError(BusinessRuleError ex){
        return new APIErrorsList(ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrorsList handleMissingServletRequestPartException(MissingServletRequestPartException ex){
        return new APIErrorsList(ex.getMessage());
    }

}
