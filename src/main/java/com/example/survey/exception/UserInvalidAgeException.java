package com.example.survey.exception;

public class UserInvalidAgeException extends RuntimeException {

    public UserInvalidAgeException() {
        super("Age of user must be between 10 and 100");
    }

}
