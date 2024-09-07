package com.example.survey.service;

import com.example.survey.converter.UserConverter;
import com.example.survey.model.*;
import com.example.survey.repository.UserJpaRepository;
import com.example.survey.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service

public class AuthService {
        @Autowired
        UserValidator userValidator;
        @Autowired
        UserJpaRepository userJpaRepository;
        @Autowired JWTService jwtService;




        public RegistrationResponse registration (RegistrationParameter registrationParameter) {
        userValidator.validateOnCreation(registrationParameter);


        User user = new User();
        user.setName(registrationParameter.getName());
        user.setAge(registrationParameter.getAge());
        user.setGender(registrationParameter.getGender());
        user.setMail(registrationParameter.getMail());
        user.setPassword(registrationParameter.getPassword());
        user.setCreationDate(OffsetDateTime.now());

        userJpaRepository.save(user);
        UserDto userDto = UserConverter.toDto(user);
        String jwt = jwtService.createJWT(userDto);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setUserDto(userDto);
        registrationResponse.setToken(jwt);

        return registrationResponse;
    }

        public SingInResponse signIn (SignInParameter signInParameter){

        String mail = signInParameter.getMail();
        String password = signInParameter.getPassword();
        User user = userJpaRepository.findByMailAndPassword(mail,password);

        if(user == null){
            throw  new RuntimeException("пользователь не найден");
        }

        UserDto userDto = UserConverter.toDto(user);
        String jwt = jwtService.createJWT(userDto);

        SingInResponse singInResponse = new SingInResponse();
        singInResponse.setUserDto(userDto);
        singInResponse.setToken(jwt);

        return singInResponse;

        }
}
