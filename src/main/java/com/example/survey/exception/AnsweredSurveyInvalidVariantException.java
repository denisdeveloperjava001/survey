package com.example.survey.exception;

public class AnsweredSurveyInvalidVariantException extends RuntimeException {

    public AnsweredSurveyInvalidVariantException(int answerNumber) {
        super("Answer number " + answerNumber + " must be chosen from the options provided");
    }

}
