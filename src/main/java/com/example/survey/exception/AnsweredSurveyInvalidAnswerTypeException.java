package com.example.survey.exception;

public class AnsweredSurveyInvalidAnswerTypeException extends RuntimeException {

    public AnsweredSurveyInvalidAnswerTypeException(int questionNumber) {
        super("question type of question number " + questionNumber + " does not match answer type");
    }

}
