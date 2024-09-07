package com.example.survey.controller;

import com.example.survey.converter.SurveyConverter;
import com.example.survey.converter.UserConverter;
import com.example.survey.model.*;
import com.example.survey.service.JWTService;
import com.example.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService service;
    @Autowired
    JWTService jwtService;

    @PostMapping("/survey")
    public SurveyDto createSurvey (@RequestHeader("Authorization") String token,@RequestBody SurveyCreateParameterDto createParameterDto) {
        SurveyCreateParameter surveyCreateParameter = SurveyConverter.toEntity(createParameterDto);
        jwtService.validationJWT(token, surveyCreateParameter.getOwnerId());
        Survey survey = service.saveSurvey(surveyCreateParameter);
        SurveyDto surveyDto = SurveyConverter.toDto(survey);

        return surveyDto;
    }

    @GetMapping("/survey")
    public SurveyDto getSurvey (@RequestParam UUID id){
        Survey survey = service.getSurvey(id);
        SurveyDto surveyDto = SurveyConverter.toDto(survey);
        return surveyDto;
    }

    @DeleteMapping("/survey")
    public void deleteSurvey (@RequestHeader("Authorization") String token,@RequestParam UUID id){
        User user = service.getUserBySurveyId(id);
        jwtService.validationJWT(token, user.getId());
        service.deleteSurvey(id);

    }


    @GetMapping("/survey/findByUserId") // получаем опросники кроторые создал пользователь
    public Page<SurveyDto> getSurveysByUserId (
            @RequestParam UUID userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "creationDate") String sortBy){
        Page<Survey> surveys = service.getSurveysByUserId(userId, pageNo, pageSize, sortBy);
        Page<SurveyDto> surveysDto = surveys.map(SurveyConverter::toDto);
        return surveysDto;
    }


    @GetMapping("/survey/findAll")
    public Page<SurveyDto> getSurveyFindAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "creationDate")
            String sortBy) {
        Page<Survey> surveys = service.getSurveyFindAll(pageNo, pageSize, sortBy);
        Page<SurveyDto> surveysDto = surveys.map(SurveyConverter::toDto);
        return surveysDto;
    }

    @GetMapping("/survey/search")
    public Page<SurveyDto> searchSurveysByTitle (
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "creationDate") String sortBy){
        Page<Survey> foundSurveys = service.searchSurveysByTitle(title,pageNo,pageSize,sortBy);
        Page<SurveyDto> foundSurveysDto = foundSurveys.map(SurveyConverter::toDto);
        return foundSurveysDto;
    }








}
