package com.example.survey.converter;

import com.example.survey.model.*;
import com.example.survey.model.type_of_question.TypeOfQuestion;
import com.example.survey.model.type_of_question.TypeOfQuestionDto;

import java.util.*;

public class SurveyConverter {

    public static SurveyCreateParameter toEntity(SurveyCreateParameterDto surveyCreateParameterDto) {

        SurveyCreateParameter surveyCreateParameter = new SurveyCreateParameter();

        surveyCreateParameter.setOwnerId(surveyCreateParameterDto.getOwnerId());
        surveyCreateParameter.setTitle(surveyCreateParameterDto.getTitle());

        List<TypeOfQuestion> questions = new ArrayList<>();
        for(int i = 0; i < surveyCreateParameterDto.getQuestions().size(); i++){
            questions.add(TypeOfQuestionConverter.toEntity(surveyCreateParameterDto.getQuestions().get(i)));
        }

        surveyCreateParameter.getQuestions().addAll(questions);

        return surveyCreateParameter;
    }

    public static SurveyDto toDto (Survey survey){

        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(survey.getId());
        surveyDto.setOwner(UserConverter.toDto(survey.getOwner()));
        surveyDto.setTitle(survey.getTitle());

        List<TypeOfQuestionDto> questionsDto = new ArrayList<>();
        for(int i = 0; i < survey.getQuestions().size(); i++){
            questionsDto.add(TypeOfQuestionConverter.toDto(survey.getQuestions().get(i)));
        }
        surveyDto.getQuestions().addAll(questionsDto);

        surveyDto.setCreationDate(survey.getCreationDate());
        surveyDto.setRespondentsCount(survey.getRespondentsCount());

        return surveyDto;

    }





}
