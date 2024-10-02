package com.example.survey.exception;

public class AnsweredSurveyAnswerSizeException extends RuntimeException {

    public AnsweredSurveyAnswerSizeException() {
        super("The number of answers does not correspond to the number of questions");
    }

}
