package com.example.survey.service;

import com.example.survey.model.Survey;
import com.example.survey.model.SurveyCreateParameter;
import com.example.survey.model.User;
import com.example.survey.repository.SurveyJpaRepository;
import com.example.survey.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class SurveyService {

    @Autowired
    private SurveyJpaRepository surveyJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    public Survey save(SurveyCreateParameter surveyCreateParameter) {
        Survey survey = new Survey();
        survey.setOwner(userJpaRepository.findById(surveyCreateParameter.getOwnerId()).get());
        survey.setTitle(surveyCreateParameter.getTitle());
        survey.setQuestions(surveyCreateParameter.getQuestions());
        survey.setCreationDate(OffsetDateTime.now());

        surveyJpaRepository.save(survey);

        return survey;
    }

    public Survey get(UUID id) {
        return surveyJpaRepository.findById(id).orElse(null);
    }

     public void delete(UUID id) {
        surveyJpaRepository.deleteById(id);
    }

    public Page<Survey> getByUserId(UUID userId, int pageNo, int pageSize, String sortBy) {
         Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

         return surveyJpaRepository.findByOwnerId(userId, pageable);
    }

    public Page<Survey> getSurveyFindAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return surveyJpaRepository.findAll(pageable);
    }

    public Page<Survey> searchSurveysByTitle(String title, int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return surveyJpaRepository.findByTitleContainingIgnoreCase(title,pageable);
    }

    public User getUserBySurveyId(UUID surveyId) {
        return surveyJpaRepository.findOwnerById(surveyId);
    }

}
