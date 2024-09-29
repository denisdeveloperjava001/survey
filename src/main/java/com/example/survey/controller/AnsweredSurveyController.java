package com.example.survey.controller;


import com.example.survey.converter.AnsweredSurveyConverter;
import com.example.survey.converter.SurveyConverter;
import com.example.survey.model.*;
import com.example.survey.repository.AnsweredSurveyJpaRepository;
import com.example.survey.service.AnsweredSurveyService;
import com.example.survey.service.AnsweredSurveyService;
import com.example.survey.service.JWTService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AnsweredSurveyController {

    @Autowired
    private AnsweredSurveyService service;
    @Autowired
    private JWTService jwtService;


    @PostMapping("/answeredSurvey")
    public AnsweredSurveyDto createAnsweredSurvey (@RequestHeader("Authorization") String token,@RequestBody AnsweredSurveyCreationParameterDto answeredSurveyCreationParameterDto) {
        jwtService.validationJWT(token, answeredSurveyCreationParameterDto.getUserId());
        AnsweredSurveyCreateParameter answeredSurveyCreateParameter = AnsweredSurveyConverter.toEntity(answeredSurveyCreationParameterDto);
        AnsweredSurvey answeredSurvey = service.saveAnsweredSurvey(answeredSurveyCreateParameter);
        AnsweredSurveyDto answeredSurveyDto = AnsweredSurveyConverter.toDto(answeredSurvey);

        return answeredSurveyDto;
    }

    @GetMapping("/answeredSurvey")
    public AnsweredSurveyDto getAnsweredSurvey (@RequestParam UUID userId, @RequestParam UUID surveyId){
         AnsweredSurvey answeredSurvey = service.getAnsweredSurvey(userId,surveyId);
         AnsweredSurveyDto answeredSurveyDto = AnsweredSurveyConverter.toDto(answeredSurvey);
        return answeredSurveyDto;
    }

    @GetMapping("/answeredSurvey/getByUserId")  //получить все отвеченные опросники по одному пользователю
    public Page<AnsweredSurveyDto> getByUserId (
            @RequestParam UUID userId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<AnsweredSurvey> answeredSurveys = service.getByUserId(userId, pageNo, pageSize);
        Page<AnsweredSurveyDto> answeredSurveysDto = answeredSurveys.map(AnsweredSurveyConverter::toDto);

        return answeredSurveysDto;
    }

}
