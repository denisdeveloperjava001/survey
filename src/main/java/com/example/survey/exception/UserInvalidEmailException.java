package com.example.survey.exception;

public class UserInvalidEmailException extends RuntimeException {

    public UserInvalidEmailException() {
        super("Invalid email address");
    }

}
