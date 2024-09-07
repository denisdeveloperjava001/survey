package com.example.survey.validation;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnsweredSurveyValidator {

    @Autowired
    SurveyJpaRepository surveyJpaRepository;


    public void validateOnCreation (AnsweredSurveyCreateParameter createParameter){
        Survey survey = surveyJpaRepository.findById(createParameter.getSurveyId()).get();

        if(createParameter.getAnswers().size() != survey.getQuestions().size()) {
            throw new RuntimeException("не на все вопросы ответил");
        }



    }
}
