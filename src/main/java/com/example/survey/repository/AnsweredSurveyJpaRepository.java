package com.example.survey.repository;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.Survey;
import com.example.survey.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnsweredSurveyJpaRepository extends JpaRepository<AnsweredSurvey, AnsweredSurvey.CompositeKey> {
    Page<AnsweredSurvey> findAllByUserId (UUID userId,Pageable pageable);

    List<AnsweredSurvey> findAllBySurveyId(UUID surveyId);

    Page<AnsweredSurvey> findAllBySurveyId(UUID surveiId , Pageable pageable);



}
