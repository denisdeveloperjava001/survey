package com.example.survey.converter;

import com.example.survey.model.Survey;
import com.example.survey.model.SurveyCreateParameter;
import com.example.survey.model.SurveyCreateParameterDto;
import com.example.survey.model.SurveyDto;
import com.example.survey.model.type_of_question.TypeOfQuestion;
import com.example.survey.model.type_of_question.TypeOfQuestionDto;

import java.util.ArrayList;
import java.util.List;

public class SurveyConverter {

    public static SurveyCreateParameter toEntity(SurveyCreateParameterDto surveyCreateParameterDto) {
        SurveyCreateParameter surveyCreateParameter = new SurveyCreateParameter();

        surveyCreateParameter.setOwnerId(surveyCreateParameterDto.getOwnerId());
        surveyCreateParameter.setTitle(surveyCreateParameterDto.getTitle());

        List<TypeOfQuestion> questions = new ArrayList<>();
        for(TypeOfQuestionDto questionDto : surveyCreateParameterDto.getQuestions()) {
            questions.add(TypeOfQuestionConverter.toEntity(questionDto));
        }
        surveyCreateParameter.getQuestions().addAll(questions);

        return surveyCreateParameter;
    }

    public static SurveyDto toDto(Survey survey) {
        SurveyDto surveyDto = new SurveyDto();

        surveyDto.setId(survey.getId());
        surveyDto.setOwner(UserConverter.toDto(survey.getOwner()));
        surveyDto.setTitle(survey.getTitle());
        surveyDto.setCreationDate(survey.getCreationDate());
        surveyDto.setRespondentsCount(survey.getRespondentsCount());

        List<TypeOfQuestionDto> questionsDto = new ArrayList<>();
        for(TypeOfQuestion question : survey.getQuestions()){
            questionsDto.add(TypeOfQuestionConverter.toDto(question));
        }
        surveyDto.getQuestions().addAll(questionsDto);

        return surveyDto;
    }

}
