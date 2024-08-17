package com.example.survey.controller;

import com.example.survey.model.Survey;
import com.example.survey.model.SurveyCreateParameter;
import com.example.survey.model.User;
import com.example.survey.model.UserCreateParameter;
import com.example.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService service;

    @PostMapping("/survey")
    public Survey createSurvey (@RequestBody SurveyCreateParameter createParameter) {
        Survey survey = service.createSurvey(createParameter);
        return survey;
    }

    @GetMapping("/survey")
    public Survey getSurvey (@RequestParam UUID id){
        Survey survey = service.getSurvey(id);
        return survey;
    }

    @DeleteMapping("/survey")
    public void deleteSurvey (@RequestParam UUID id){
        service.deleteSurvey(id);
    }

    @GetMapping("/survey/findByUserId") // получаем опросники кроторые создал пользователь
    public List<Survey> getSurveysByUserId (@RequestParam UUID userId){
        List<Survey> surveys = service.getSurveysByUserId(userId);
        return surveys;
    }

    @GetMapping("/survey/findAll") // получаем все существующие опросники(главная страница)
    public List<Survey> getSurveyFindAll (){
        List<Survey> allSurveys = service.getSurveyFindAll();

        return allSurveys;
    }







}
