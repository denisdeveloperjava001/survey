package com.example.survey.controller;

import com.example.survey.converter.UserConverter;
import com.example.survey.model.User;
import com.example.survey.model.UserDto;
import com.example.survey.model.UserUpdateParameter;
import com.example.survey.model.UserUpdateParameterDto;
import com.example.survey.service.JWTService;
import com.example.survey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTService jwtService;

    @GetMapping
    public UserDto get(@RequestParam UUID id) {
        User user = service.getOrThrow(id);
        return UserConverter.toDto(user);
    }

    @DeleteMapping
    public void delete(@RequestHeader("Authorization") String token,
                       @RequestParam UUID id) {
        jwtService.validate(token, id);
        service.delete(id);
    }

    @PutMapping
    public UserDto update(@RequestHeader("Authorization") String token,
                          @RequestParam UUID id,
                          @RequestBody UserUpdateParameterDto userUpdateParameterDto) {
        jwtService.validate(token, id);
        UserUpdateParameter userUpdateParameter = UserConverter.toEntity(userUpdateParameterDto);
        User user = service.update(id, userUpdateParameter);
        return UserConverter.toDto(user);
    }

    @GetMapping("/bySurvey")
    public Page<UserDto> getByAnsweredSurvey(@RequestParam UUID surveyId,
                                             @RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> users = service.getByAnsweredSurvey(surveyId, pageNo, pageSize);
        return users.map(UserConverter::toDto);
    }

}
