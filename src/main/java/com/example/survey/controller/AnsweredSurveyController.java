package com.example.survey.controller;


import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.service.AnsweredSurveyService;
import com.example.survey.service.AnsweredSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AnsweredSurveyController {

    @Autowired
    private AnsweredSurveyService service;

    @PostMapping("/answeredSurvey")
    public AnsweredSurvey createAnsweredSurvey (@RequestBody AnsweredSurveyCreateParameter createParameter) {
        AnsweredSurvey answeredSurvey = service.createAnsweredSurvey(createParameter);
        return answeredSurvey;
    }

    @GetMapping("/answeredSurvey")
    public AnsweredSurvey getAnsweredSurvey (@RequestParam UUID userId, @RequestParam UUID surveyId){
         AnsweredSurvey answeredSurvey = service.getAnsweredSurvey(userId,surveyId);
        return answeredSurvey;
    }

    @GetMapping("/answeredSurvey/getByUserId")  //получить все отвеченные опросники по одному пользователю
    public List<AnsweredSurvey> getByUserId (@RequestParam UUID userId) {
        List<AnsweredSurvey> answeredSurveys = service.getByUserId(userId);
        return answeredSurveys;
    }






}
