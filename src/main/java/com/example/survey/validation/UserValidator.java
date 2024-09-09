package com.example.survey.validation;

import com.example.survey.model.RegistrationParameter;
import com.example.survey.model.User;
import com.example.survey.model.UserUpdateParameter;
import com.example.survey.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidator {

    @Autowired
    UserJpaRepository userJpaRepository;

    public void validateOnCreation(RegistrationParameter registrationParameter){
        ageValidation(registrationParameter.getAge());
        emailValidation(registrationParameter.getMail());
        passwordValidation(registrationParameter.getPassword());
        emailExistenceValidation(registrationParameter.getMail());
    }

    public void validateOnUpdate(UUID userId, UserUpdateParameter userUpdateParameter) {
        ageValidation(userUpdateParameter.getAge());
        emailValidation(userUpdateParameter.getMail());
        passwordValidation(userUpdateParameter.getPassword());
        emailExistenceValidation(userId, userUpdateParameter.getMail());
    }


    private void ageValidation (int age){

        if (age < 10 || age > 100) {
            throw new RuntimeException("не соответствует диапазону допустимого возраста");
        }
    }

    private void emailValidation(String mail) {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(mail);
        if(!matcher.matches()){
            throw new RuntimeException("емэйл не соответствует требованиям");
        }
    }

    private void passwordValidation(String password ){

        String regexPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // наличие хотя бы одной строчной буквы
                                                                // наличие хотя бы одной заглавной буквы
                                                                // от 8 до 20 символов
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            throw new RuntimeException("пароль не соответствует требованиям,наличие хотя бы одной строчной буквы," +
                    "наличие хотя бы одной заглавной буквы,от 8 до 20 символов" );
        }
    }

    private void emailExistenceValidation (String mail){

        if(userJpaRepository.existsByMail(mail)){
            throw new RuntimeException("пользователь с такой почтой уже существует");
        }

    }

    private void emailExistenceValidation (UUID userId , String mail){
        if(userJpaRepository.existsByMailAndIdNot(mail, userId)){
            throw new RuntimeException("пользователь с такой почтой уже существует");
        }
    }



}
