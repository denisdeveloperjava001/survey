package com.example.survey.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Authorization exception, invalid token");
    }

}
