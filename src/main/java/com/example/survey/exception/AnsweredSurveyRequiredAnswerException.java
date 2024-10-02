package com.example.survey.exception;

public class AnsweredSurveyRequiredAnswerException extends RuntimeException {

    public AnsweredSurveyRequiredAnswerException(int answerNumber) {
        super("Answer number " + answerNumber + " is required");
    }

}
