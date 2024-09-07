package com.example.survey.service;

import com.example.survey.model.Survey;
import com.example.survey.model.SurveyCreateParameter;
import com.example.survey.model.User;
import com.example.survey.repository.SurveyJpaRepository;
import com.example.survey.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private SurveyJpaRepository surveyJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;


    public Survey saveSurvey(SurveyCreateParameter surveyCreateParameter) {
        Survey survey = new Survey();
        survey.setOwner(userJpaRepository.findById(surveyCreateParameter.getOwnerId()).get());    //новому опроснику присваем id из запроса
        survey.setTitle(surveyCreateParameter.getTitle());
        survey.setQuestions(surveyCreateParameter.getQuestions());
        survey.setCreationDate(OffsetDateTime.now());

        surveyJpaRepository.save(survey);
      return survey;
    }


    public Survey getSurvey(UUID id) {

        return surveyJpaRepository.findById(id).orElse(null);
    }




     public void deleteSurvey(UUID id) {
        surveyJpaRepository.deleteById(id);
    }


     public Page<Survey> getSurveysByUserId(UUID userId, int pageNo, int pageSize, String sortBy) {
         Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
         Page<Survey> surveys = surveyJpaRepository.findByOwnerId(userId, pageable);

        return surveys;
    }



    public Page<Survey> getSurveyFindAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return surveyJpaRepository.findAll(pageable);
    }



     public Page<Survey> searchSurveysByTitle(String title, int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Survey> foundSurveys = surveyJpaRepository.findByTitleContainingIgnoreCase(title,pageable);
        return foundSurveys;
    }

    public User getUserBySurveyId (UUID surveyId){
        User user = surveyJpaRepository.findOwnerById(surveyId);

        return user;
    }

}
