package com.example.survey.repository;

import com.example.survey.model.Survey;
import com.example.survey.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyJpaRepository extends JpaRepository<Survey, UUID> {

    Page<Survey> findByOwnerId(UUID userId, Pageable pageable);

    Page<Survey> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("select s.owner from Survey s where s.id = ?1")
    User findOwnerById(UUID surveyId);

}
