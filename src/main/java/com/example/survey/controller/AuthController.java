package com.example.survey.controller;

import com.example.survey.converter.AuthConverter;
import com.example.survey.model.*;
import com.example.survey.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public RegistrationResponseDto registration(@RequestBody RegistrationParameterDto registrationParameterDto) {
        RegistrationParameter registrationParameter = AuthConverter.toEntity(registrationParameterDto);
        RegistrationResponse registrationResponse = authService.registration(registrationParameter);
        return AuthConverter.toDto(registrationResponse);
    }

    @PostMapping("/login")
    public SingInResponseDto login(@RequestBody SignInParameterDto signInParameterDto) {
        SignInParameter signInParameter = AuthConverter.toEntity(signInParameterDto);
        SingInResponse singInResponse = authService.login(signInParameter);
        return AuthConverter.toDto(singInResponse);
    }

}
