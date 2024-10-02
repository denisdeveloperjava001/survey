package com.example.survey.exception;

public class UnknownTypeException extends RuntimeException {

    public UnknownTypeException() {
        super("Unable to convert, unknown type");
    }
}
