package com.zupchallenge.SpringBootChallenge.shared.errors.http;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Data
public class APIErrorsList {
    @Getter
    private List<String> errors;

    public APIErrorsList(String message) {
        this.errors = Arrays.asList(message);
    }

    public APIErrorsList(List<String> errors) {
        this.errors = errors;
    }


}
