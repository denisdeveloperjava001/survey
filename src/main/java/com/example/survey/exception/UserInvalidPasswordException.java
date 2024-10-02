package com.example.survey.exception;

public class UserInvalidPasswordException extends RuntimeException {

    public UserInvalidPasswordException() {
        super("Password does not meet the requirements: at least one lowercase letter, at least one uppercase letter, from 8 to 20 characters");
    }

}
