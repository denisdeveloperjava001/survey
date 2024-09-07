package com.example.survey.converter;

import com.example.survey.controller.SurveyController;
import com.example.survey.model.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SurveyConverter {

    public static SurveyCreateParameter toEntity(SurveyCreateParameterDto surveyCreateParameterDto) {

        SurveyCreateParameter surveyCreateParameter = new SurveyCreateParameter();

        surveyCreateParameter.setOwnerId(surveyCreateParameterDto.getOwnerId());
        surveyCreateParameter.setTitle(surveyCreateParameterDto.getTitle());

        surveyCreateParameter.getQuestions().addAll(surveyCreateParameterDto.getQuestions());

        return surveyCreateParameter;
    }

    public static SurveyDto toDto (Survey survey){

        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(survey.getId());
        surveyDto.setOwner(UserConverter.toDto(survey.getOwner()));
        surveyDto.setTitle(survey.getTitle());
        survey.getQuestions().addAll(surveyDto.getQuestions());
        surveyDto.setCreationDate(survey.getCreationDate());
        surveyDto.setRespondentsCount(survey.getRespondentsCount());

        return surveyDto;

    }





}
