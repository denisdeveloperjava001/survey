package com.example.survey.exception;

public class AnsweredSurveyNotFoundException extends RuntimeException {

    public AnsweredSurveyNotFoundException() {
        super("Answered survey not found");
    }

}
