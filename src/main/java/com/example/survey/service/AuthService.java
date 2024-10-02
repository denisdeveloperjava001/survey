package com.example.survey.service;

import com.example.survey.converter.UserConverter;
import com.example.survey.exception.UserNotFoundException;
import com.example.survey.model.*;
import com.example.survey.repository.UserJpaRepository;
import com.example.survey.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class AuthService {
        @Autowired
        private UserValidator userValidator;

        @Autowired
        private UserJpaRepository userJpaRepository;

        @Autowired
        private JWTService jwtService;

        public RegistrationResponse registration(RegistrationParameter registrationParameter) {
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
        String jwt = jwtService.create(userDto);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setUser(userDto);
        registrationResponse.setToken(jwt);

        return registrationResponse;
    }

        public SingInResponse login(SignInParameter signInParameter) {
        User user = userJpaRepository.findByMailAndPassword(signInParameter.getMail(), signInParameter.getPassword());

        if(user == null){
            throw new UserNotFoundException();
        }

        UserDto userDto = UserConverter.toDto(user);
        String jwt = jwtService.create(userDto);

        SingInResponse singInResponse = new SingInResponse();
        singInResponse.setUser(userDto);
        singInResponse.setToken(jwt);

        return singInResponse;
    }

}
