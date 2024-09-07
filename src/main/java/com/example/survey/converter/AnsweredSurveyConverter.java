package com.example.survey.converter;

import com.example.survey.model.*;

import java.util.List;

public class AnsweredSurveyConverter {

    public static AnsweredSurveyCreateParameter toEntity (AnsweredSurveyCreationParameterDto answeredSurveyCreationParameterDto){
        AnsweredSurveyCreateParameter answeredSurveyCreateParameter = new AnsweredSurveyCreateParameter();
        answeredSurveyCreateParameter.setSurveyId(answeredSurveyCreationParameterDto.getSurveyId());
        answeredSurveyCreateParameter.setUserId(answeredSurveyCreationParameterDto.getUserId());
        answeredSurveyCreationParameterDto.getAnswers().addAll(answeredSurveyCreateParameter.getAnswers());
        return answeredSurveyCreateParameter;
    }


    public static AnsweredSurveyDto toDto (AnsweredSurvey answeredSurvey){
        AnsweredSurveyDto answeredSurveyDto = new AnsweredSurveyDto();
        answeredSurveyDto.setUser(UserConverter.toDto(answeredSurvey.getUser()));
        answeredSurveyDto.setSurvey(SurveyConverter.toDto(answeredSurvey.getSurvey()));
        answeredSurvey.getAnswers().addAll(answeredSurveyDto.getAnswers());
        return answeredSurveyDto;
    }
}
