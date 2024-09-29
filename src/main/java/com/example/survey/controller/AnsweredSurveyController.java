package com.example.survey.controller;


import com.example.survey.converter.AnsweredSurveyConverter;
import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.AnsweredSurveyCreationParameterDto;
import com.example.survey.model.AnsweredSurveyDto;
import com.example.survey.service.AnsweredSurveyService;
import com.example.survey.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/answeredSurvey")
public class AnsweredSurveyController {

    @Autowired
    private AnsweredSurveyService service;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public AnsweredSurveyDto create(@RequestHeader("Authorization") String token,
                                                  @RequestBody AnsweredSurveyCreationParameterDto answeredSurveyCreationParameterDto) {
        jwtService.validate(token, answeredSurveyCreationParameterDto.getUserId());
        AnsweredSurveyCreateParameter answeredSurveyCreateParameter = AnsweredSurveyConverter.toEntity(answeredSurveyCreationParameterDto);
        AnsweredSurvey answeredSurvey = service.create(answeredSurveyCreateParameter);
        return AnsweredSurveyConverter.toDto(answeredSurvey);
    }

    @GetMapping
    public AnsweredSurveyDto get(@RequestParam UUID userId,
                                               @RequestParam UUID surveyId) {
        AnsweredSurvey answeredSurvey = service.get(userId, surveyId);
        return AnsweredSurveyConverter.toDto(answeredSurvey);
    }

    @GetMapping("/getByUserId")
    public Page<AnsweredSurveyDto> getByUserId(@RequestParam UUID userId,
                                               @RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        Page<AnsweredSurvey> answeredSurveys = service.getByUserId(userId, pageNo, pageSize);
        return answeredSurveys.map(AnsweredSurveyConverter::toDto);
    }

}
