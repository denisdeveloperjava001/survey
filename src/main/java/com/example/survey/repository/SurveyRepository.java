package com.example.survey.repository;
import com.example.survey.model.Survey;
import com.example.survey.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SurveyRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveSurvey(Survey survey) {
        em.persist(survey);
    }

    @Transactional
    public Survey getSurvey (UUID id){
        Survey survey = em.find(Survey.class , id);
        return survey;
    }

    @Transactional
    public void deleteSurvey (Survey survey){
        em.remove(survey);
    }

    @Transactional
    public List<Survey> getSurveysByUserId (User user){
        List<Survey> surveys = em.createQuery("select e from Survey e where e.owner = :owner", Survey.class)
                .setParameter("owner", user)
                .getResultList();
        return surveys;
    }

    @Transactional
    public List<Survey> getSurveyFindAll() {
        List<Survey> allSurveys = em.createQuery("select e from Survey e", Survey.class)
                .getResultList();
        return allSurveys;


    }



}
