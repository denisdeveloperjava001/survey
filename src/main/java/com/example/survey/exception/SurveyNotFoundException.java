package com.example.survey.exception;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException() {
        super("Survey not found");
    }

}
