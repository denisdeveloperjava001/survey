package com.example.survey.service;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.User;
import com.example.survey.model.UserUpdateParameter;
import com.example.survey.repository.AnsweredSurveyJpaRepository;
import com.example.survey.repository.UserJpaRepository;
import com.example.survey.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private AnsweredSurveyJpaRepository answeredSurveyJpaRepository;

    @Autowired
    private UserValidator userValidator;

    public User get(UUID id) {
        return userJpaRepository.findById(id).get();
    }

    public void delete(UUID id) {
        userJpaRepository.deleteById(id);
    }

    public User update(UUID id, UserUpdateParameter userUpdateParameter) {
        userValidator.validateOnUpdate(id, userUpdateParameter);

        User user = userJpaRepository.findById(id).get();

        user.setName(userUpdateParameter.getName());
        user.setAge(userUpdateParameter.getAge());
        user.setGender(userUpdateParameter.getGender());
        user.setMail(userUpdateParameter.getMail());
        user.setPassword(userUpdateParameter.getPassword());

        userJpaRepository.save(user);

        return user;
    }

    public Page<User> getByAnsweredSurvey(UUID surveyId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AnsweredSurvey> answeredSurveys = answeredSurveyJpaRepository.findAllBySurveyId(surveyId, pageable);
        List<User> users = answeredSurveys.stream().map(AnsweredSurvey::getUser).toList();

        return new PageImpl<>(users, pageable, answeredSurveys.getTotalElements());
    }

}
