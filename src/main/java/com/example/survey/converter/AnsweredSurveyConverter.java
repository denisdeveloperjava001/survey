package com.example.survey.converter;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.AnsweredSurveyCreationParameterDto;
import com.example.survey.model.AnsweredSurveyDto;
import com.example.survey.model.typy_of_answer.TypeOfAnswer;
import com.example.survey.model.typy_of_answer.TypeOfAnswerDto;

public class AnsweredSurveyConverter {

    public static AnsweredSurveyCreateParameter toEntity (AnsweredSurveyCreationParameterDto answeredSurveyCreationParameterDto) {
        AnsweredSurveyCreateParameter answeredSurveyCreateParameter = new AnsweredSurveyCreateParameter();
        answeredSurveyCreateParameter.setSurveyId(answeredSurveyCreationParameterDto.getSurveyId());
        answeredSurveyCreateParameter.setUserId(answeredSurveyCreationParameterDto.getUserId());

        for(TypeOfAnswerDto answerDto : answeredSurveyCreationParameterDto.getAnswers()) {
            answeredSurveyCreateParameter.getAnswers().add(TypeOfAnswerConverter.toEntity(answerDto));
        }

        return answeredSurveyCreateParameter;
    }

    public static AnsweredSurveyDto toDto (AnsweredSurvey answeredSurvey) {
        AnsweredSurveyDto answeredSurveyDto = new AnsweredSurveyDto();
        answeredSurveyDto.setUser(UserConverter.toDto(answeredSurvey.getUser()));
        answeredSurveyDto.setSurvey(SurveyConverter.toDto(answeredSurvey.getSurvey()));

        for(TypeOfAnswer answer : answeredSurvey.getAnswers()) {
            answeredSurveyDto.getAnswers().add(TypeOfAnswerConverter.toDto(answer));
        }

        return answeredSurveyDto;
    }

}
