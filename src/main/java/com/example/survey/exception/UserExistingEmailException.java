package com.example.survey.exception;

public class UserExistingEmailException extends RuntimeException {

    public UserExistingEmailException() {
        super("User with this email already exists");
    }

}
