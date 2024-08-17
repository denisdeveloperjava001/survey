package com.example.survey.service;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.model.User;
import com.example.survey.repository.AnsweredSurveyRepository;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnsweredSurveyService {

    @Autowired
    private AnsweredSurveyRepository repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyRepository surveyRepository;


    public AnsweredSurvey createAnsweredSurvey (AnsweredSurveyCreateParameter createParameter){

        Survey survey = surveyRepository.getSurvey(createParameter.getSurveyId());

        AnsweredSurvey answeredSurvey = new AnsweredSurvey();
        answeredSurvey.setUser(userRepository.getUser(createParameter.getUserId()));
        answeredSurvey.setSurvey(survey);
        answeredSurvey.setAnswers(createParameter.getAnswers());

        repository.saveAnsweredSurvey(answeredSurvey);

        survey.setRespondentsCount(survey.getRespondentsCount() + 1); // повышаем счётчик количества юзеров ответивших на данный отпросник
        surveyRepository.saveSurvey(survey);

        return answeredSurvey;
    }

    public AnsweredSurvey getAnsweredSurvey (UUID userId, UUID surveyId){
        User user = userRepository.getUser(userId);
        Survey survey = surveyRepository.getSurvey(surveyId);
        AnsweredSurvey answeredSurvey = repository.getAnsweredSurvey(user, survey);
        return answeredSurvey;
    }

    public List<AnsweredSurvey> getByUserId (UUID userId){
        User user = userRepository.getUser(userId);
        List<AnsweredSurvey> answeredSurveys = repository.getByUserId(user);
        return answeredSurveys;
    }
}
