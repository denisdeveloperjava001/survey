package com.example.survey.controller;

import com.example.survey.SurveyApplication;
import com.example.survey.converter.UserConverter;
import com.example.survey.model.*;
import com.example.survey.service.JWTService;
import com.example.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    @Autowired JWTService jwtService;


    @GetMapping("/user")
    public UserDto getUser (@RequestParam UUID id){
        User user = service.getUser(id);
        UserDto userDto = UserConverter.toDto(user);
        return userDto;
    }

    @DeleteMapping("/user")
    public void deleteUser (@RequestHeader("Authorization") String token,@RequestParam UUID id){
        jwtService.validationJWT(token, id);

        service.deleteUser(id);
    }

    @PutMapping("/user")
    public UserDto updateUser (@RequestHeader("Authorization") String token, @RequestParam UUID id, @RequestBody UserUpdateParameterDto userUpdateParameterDto){
        jwtService.validationJWT(token, id);

        UserUpdateParameter userUpdateParameter = UserConverter.toEntity(userUpdateParameterDto);
        User user = service.updateUser(id, userUpdateParameter);
        UserDto userDto = UserConverter.toDto(user);
        return userDto;
    }

    @GetMapping("/user/bySurvey")  // получаем всех юзеров по одному ответевшему опроснику
    public Page<UserDto> getUsersByAnsweredSurvey (
            @RequestParam UUID surveyId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> users = service.getUsersByAnsweredSurvey(surveyId,pageNo,pageSize);
        Page<UserDto> usersDto = users.map(UserConverter::toDto);
        return usersDto;
    }

}
