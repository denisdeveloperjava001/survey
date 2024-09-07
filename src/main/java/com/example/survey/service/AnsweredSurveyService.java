package com.example.survey.service;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.model.User;
import com.example.survey.repository.*;
import com.example.survey.validation.AnsweredSurveyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnsweredSurveyService {

    @Autowired
    private AnsweredSurveyJpaRepository answeredSurveyJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private SurveyJpaRepository surveyJpaRepository;

    @Autowired AnsweredSurveyValidator validation;



    public AnsweredSurvey saveAnsweredSurvey (AnsweredSurveyCreateParameter createParameter){
        validation.validateOnCreation(createParameter);

        User user = userJpaRepository.findById(createParameter.getUserId()).get();
        Survey survey = surveyJpaRepository.findById(createParameter.getSurveyId()).get();

        AnsweredSurvey answeredSurvey = new AnsweredSurvey();
        answeredSurvey.setUser(user);
        answeredSurvey.setSurvey(survey);
        answeredSurvey.setAnswers(createParameter.getAnswers());

        answeredSurveyJpaRepository.save(answeredSurvey);

        survey.setRespondentsCount(survey.getRespondentsCount() +1);
        surveyJpaRepository.save(survey);

         return answeredSurvey;

    }

    public AnsweredSurvey getAnsweredSurvey (UUID userId, UUID surveyId){

        AnsweredSurvey answeredSurvey = answeredSurveyJpaRepository.findById(new AnsweredSurvey.CompositeKey(userId,surveyId)).get();
        return answeredSurvey;
    }


    public Page<AnsweredSurvey> getByUserId(UUID userId, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AnsweredSurvey> answeredSurveys = answeredSurveyJpaRepository.findAllByUserId(userId,pageable);
        return answeredSurveys;
    }



}
