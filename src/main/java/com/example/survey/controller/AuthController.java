package com.example.survey.controller;

import com.example.survey.converter.AuthConverter;
import com.example.survey.converter.UserConverter;
import com.example.survey.model.*;
import com.example.survey.repository.UserJpaRepository;
import com.example.survey.service.AuthService;
import com.example.survey.service.JWTService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    JWTService jwtService;
    @Autowired
    UserJpaRepository userJpaRepository;

    @PostMapping("auth/registration")
    public RegistrationResponseDto registration (@RequestBody RegistrationParameterDto registrationParameterDto){

        RegistrationParameter registrationParameter = AuthConverter.toEntity(registrationParameterDto);
        RegistrationResponse registrationResponse = authService.registration(registrationParameter);
        RegistrationResponseDto registrationResponseDto = AuthConverter.toDto(registrationResponse);

        return registrationResponseDto;
    }


    @PostMapping("auth/signIn")
    public SingInResponseDto signIn (@RequestBody SignInParameterDto signInParameterDto) {

        SignInParameter signInParameter = AuthConverter.toEntity(signInParameterDto);
        SingInResponse singInResponse = authService.signIn(signInParameter);
        SingInResponseDto singInResponseDto = AuthConverter.toDto(singInResponse);

        return singInResponseDto;
    }

}
