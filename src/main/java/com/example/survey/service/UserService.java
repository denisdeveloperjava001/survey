package com.example.survey.service;

import com.example.survey.controller.UserController;
import com.example.survey.model.User;
import com.example.survey.model.UserCreateParameter;
import com.example.survey.model.UserUpdateParameter;
import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(UserCreateParameter createParameter) {
        User user = new User();
        user.setName(createParameter.getName());
        user.setAge(createParameter.getAge());
        user.setGender(createParameter.getGender());
        user.setMail(createParameter.getMail());
        user.setPassword(createParameter.getPassword());
        user.setCreationDate(OffsetDateTime.now());

        repository.saveUser(user);

        return user;
    }

    public User getUser (UUID id){
        User user = repository.getUser(id);
        return user;
    }

    public void deleteUser (UUID id){
        User user = repository.getUser(id);
        repository.deleteUser(user);
    }

    public User updateUser (UUID id, UserUpdateParameter userUpdateParameter) {
        User user = repository.getUser(id);

        user.setName(userUpdateParameter.getName());
        user.setAge(userUpdateParameter.getAge());
        user.setGender(userUpdateParameter.getGender());
        user.setMail(userUpdateParameter.getMail());
        user.setPassword(userUpdateParameter.getPassword());

        repository.saveUser(user);

        return user;
    }

}
