package com.example.survey.service;

import com.example.survey.model.*;
import com.example.survey.repository.*;
import com.example.survey.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private SurveyJpaRepository surveyJpaRepository;

    @Autowired
    private AnsweredSurveyJpaRepository answeredSurveyJpaRepository;

    @Autowired
    private UserValidator userValidator;


    public User getUser (UUID id){
        User user = userJpaRepository.findById(id).get();
        return user;
    }

    public void deleteUser (UUID id){
        userJpaRepository.deleteById(id);
    }

    public User updateUser (UUID id, UserUpdateParameter userUpdateParameter) {
        User user = userJpaRepository.findById(id).get();

        user.setName(userUpdateParameter.getName());
        user.setAge(userUpdateParameter.getAge());
        user.setGender(userUpdateParameter.getGender());
        user.setMail(userUpdateParameter.getMail());
        user.setPassword(userUpdateParameter.getPassword());

        userJpaRepository.save(user);

        return user;
    }

    public Page<User> getUsersByAnsweredSurvey (UUID surveyId, int pageNo, int pageSize){ // получить всех юзеров ответивших на отпросник
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AnsweredSurvey> answeredSurveys = answeredSurveyJpaRepository.findAllBySurveyId(surveyId,pageable);
        List<User> users = answeredSurveys.stream().map(AnsweredSurvey::getUser).toList();
        PageImpl<User> userPage = new PageImpl<>(users , pageable , answeredSurveys.getTotalElements());

        return userPage;
    }

}
