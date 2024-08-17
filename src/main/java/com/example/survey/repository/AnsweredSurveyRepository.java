package com.example.survey.repository;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.Survey;
import com.example.survey.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AnsweredSurveyRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveAnsweredSurvey (AnsweredSurvey answeredSurvey){
        em.persist(answeredSurvey);
    }

    @Transactional
    public AnsweredSurvey getAnsweredSurvey(User user, Survey survey){
        AnsweredSurvey answeredSurvey = em.createQuery("select e from AnsweredSurvey e where e.user = :user and e.survey = :survey", AnsweredSurvey.class)
                .setParameter("user", user)
                .setParameter("survey", survey)
                .getSingleResult();

        return answeredSurvey;
    }

    @Transactional
    public List<AnsweredSurvey> getByUserId (User user){
        List<AnsweredSurvey> ansveredSurveys = em.createQuery("select e from AnsweredSurvey e where e.user = :user", AnsweredSurvey.class)
                .setParameter("user", user)
                .getResultList();
        return ansveredSurveys;
    }

    @Transactional
    public List<AnsweredSurvey> getUsersByAnsweredSurvey (Survey survey){
        List<AnsweredSurvey> answeredSurveys = em.createQuery("select e from AnsweredSurvey e where e.survey = :survey", AnsweredSurvey.class)
                .setParameter("survey", survey)
                .getResultList();
        return answeredSurveys;
    }


}
