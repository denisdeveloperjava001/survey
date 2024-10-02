package com.example.survey.service;

import com.example.survey.exception.AnsweredSurveyNotFoundException;
import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.model.User;
import com.example.survey.repository.AnsweredSurveyJpaRepository;
import com.example.survey.repository.SurveyJpaRepository;
import com.example.survey.validation.AnsweredSurveyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnsweredSurveyService {

    @Autowired
    private AnsweredSurveyJpaRepository answeredSurveyJpaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyJpaRepository surveyJpaRepository;

    @Autowired
    private AnsweredSurveyValidator validation;

    public AnsweredSurvey create(AnsweredSurveyCreateParameter createParameter) {
        validation.validateOnCreation(createParameter);

        User user = userService.getOrThrow(createParameter.getUserId());
        Survey survey = surveyService.getOrThrow(createParameter.getSurveyId());

        AnsweredSurvey answeredSurvey = new AnsweredSurvey();
        answeredSurvey.setUser(user);
        answeredSurvey.setSurvey(survey);
        answeredSurvey.setAnswers(createParameter.getAnswers());

        answeredSurveyJpaRepository.save(answeredSurvey);

        survey.setRespondentsCount(survey.getRespondentsCount() + 1);

        surveyJpaRepository.save(survey);

        return answeredSurvey;
    }

    public AnsweredSurvey getOrThrow(UUID userId, UUID surveyId) {
        return answeredSurveyJpaRepository.findById(new AnsweredSurvey.CompositeKey(userId, surveyId))
                .orElseThrow(AnsweredSurveyNotFoundException::new);
    }

    public Page<AnsweredSurvey> getByUserId(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return answeredSurveyJpaRepository.findAllByUserId(userId, pageable);
    }

}
