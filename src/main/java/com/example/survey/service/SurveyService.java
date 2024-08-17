package com.example.survey.service;

import com.example.survey.model.Survey;
import com.example.survey.model.SurveyCreateParameter;
import com.example.survey.model.User;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repository;
    @Autowired
    private UserRepository userRepository;


    public Survey createSurvey (SurveyCreateParameter surveyCreateParameter){
        Survey survey = new Survey();
        survey.setOwner(userRepository.getUser(surveyCreateParameter.getOwnerId()));
        survey.setTitle(surveyCreateParameter.getTitle());
        survey.setQuestions(surveyCreateParameter.getQuestions());
        survey.setCreationDate(OffsetDateTime.now());

        repository.saveSurvey(survey);

        return survey;
    }

    public Survey getSurvey (UUID id){
        Survey survey = repository.getSurvey(id);
        return survey;
    }

    public void deleteSurvey (UUID id){
        Survey survey = repository.getSurvey(id);
        repository.deleteSurvey(survey);
    }

    public List<Survey> getSurveysByUserId (UUID userId){
        User user = userRepository.getUser(userId);
        List<Survey> surveys = repository.getSurveysByUserId(user);
        return surveys;
    }

    public List<Survey> getSurveyFindAll() {
        List<Survey> allSurveys = repository.getSurveyFindAll();
        return allSurveys;
    }


}
