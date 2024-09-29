package com.example.survey.converter;

import com.example.survey.model.*;

import java.util.List;

public class AnsweredSurveyConverter {

    public static AnsweredSurveyCreateParameter toEntity (AnsweredSurveyCreationParameterDto answeredSurveyCreationParameterDto){
        AnsweredSurveyCreateParameter answeredSurveyCreateParameter = new AnsweredSurveyCreateParameter();
        answeredSurveyCreateParameter.setSurveyId(answeredSurveyCreationParameterDto.getSurveyId());
        answeredSurveyCreateParameter.setUserId(answeredSurveyCreationParameterDto.getUserId());
        for(int i = 0; i<answeredSurveyCreationParameterDto.getAnswers().size(); i++){
            answeredSurveyCreateParameter.getAnswers().add(TypeOfAnswerConverter.toEntity(answeredSurveyCreationParameterDto.getAnswers().get(i)));
        }
        return answeredSurveyCreateParameter;
    }


    public static AnsweredSurveyDto toDto (AnsweredSurvey answeredSurvey){
        AnsweredSurveyDto answeredSurveyDto = new AnsweredSurveyDto();
        answeredSurveyDto.setUser(UserConverter.toDto(answeredSurvey.getUser()));
        answeredSurveyDto.setSurvey(SurveyConverter.toDto(answeredSurvey.getSurvey()));
        for(int i = 0; i < answeredSurvey.getAnswers().size(); i++){
            answeredSurveyDto.getAnswers().add(TypeOfAnswerConverter.toDto(answeredSurvey.getAnswers().get(i)));
        }
        return answeredSurveyDto;
    }
}
