package com.example.survey.controller;

import com.example.survey.SurveyApplication;
import com.example.survey.model.User;
import com.example.survey.model.UserCreateParameter;
import com.example.survey.model.UserUpdateParameter;
import com.example.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/user")
    public User createUser (@RequestBody UserCreateParameter createParameter) {
        User user = service.createUser(createParameter);
        return user;
    }

    @GetMapping("/user")
    public User getUser (@RequestParam UUID id){
        User user = service.getUser(id);
        return user;
    }

    @DeleteMapping("/user")
    public void deleteUser (@RequestParam UUID id){
       service.deleteUser(id);
    }

    @PutMapping("/user")
    public User updateUser (@RequestParam UUID id, @RequestBody UserUpdateParameter userUpdateParameter){
        User user = service.updateUser(id, userUpdateParameter);
        return user;
    }

}
