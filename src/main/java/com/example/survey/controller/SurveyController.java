package com.example.survey.controller;

import com.example.survey.converter.SurveyConverter;
import com.example.survey.model.*;
import com.example.survey.service.JWTService;
import com.example.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/survey")
public class SurveyController {

    @Autowired
    private SurveyService service;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public SurveyDto create(@RequestHeader("Authorization") String token,
                            @RequestBody SurveyCreateParameterDto createParameterDto) {
        jwtService.validate(token, createParameterDto.getOwnerId());
        SurveyCreateParameter surveyCreateParameter = SurveyConverter.toEntity(createParameterDto);
        Survey survey = service.save(surveyCreateParameter);
        return SurveyConverter.toDto(survey);
    }

    @GetMapping
    public SurveyDto get(@RequestParam UUID id) {
        Survey survey = service.get(id);
        return SurveyConverter.toDto(survey);
    }

    @DeleteMapping
    public void delete(@RequestHeader("Authorization") String token,
                       @RequestParam UUID id) {
        User user = service.getUserBySurveyId(id);
        jwtService.validate(token, user.getId());
        service.delete(id);
    }

    @GetMapping("/findByUserId")
    public Page<SurveyDto> getByUserId(@RequestParam UUID userId,
                                       @RequestParam(defaultValue = "0") int pageNo,
                                       @RequestParam(defaultValue = "10") int pageSize,
                                       @RequestParam(defaultValue = "creationDate") String sortBy) {
        Page<Survey> surveys = service.getByUserId(userId, pageNo, pageSize, sortBy);
        return surveys.map(SurveyConverter::toDto);
    }

    @GetMapping("/findAll")
    public Page<SurveyDto> getFindAll(@RequestParam(defaultValue = "0") int pageNo,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "creationDate") String sortBy) {
        Page<Survey> surveys = service.getSurveyFindAll(pageNo, pageSize, sortBy);
        return surveys.map(SurveyConverter::toDto);
    }

    @GetMapping("/search")
    public Page<SurveyDto> searchByTitle(@RequestParam String title,
                                         @RequestParam(defaultValue = "0") int pageNo,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "creationDate") String sortBy) {
        Page<Survey> foundSurveys = service.searchSurveysByTitle(title, pageNo, pageSize, sortBy);
        return foundSurveys.map(SurveyConverter::toDto);
    }

}
