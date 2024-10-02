package com.example.survey.validation;

import com.example.survey.exception.UserExistingEmailException;
import com.example.survey.exception.UserInvalidAgeException;
import com.example.survey.exception.UserInvalidEmailException;
import com.example.survey.exception.UserInvalidPasswordException;
import com.example.survey.model.RegistrationParameter;
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
    private UserJpaRepository userJpaRepository;

    public void validateOnCreation(RegistrationParameter registrationParameter) {
        validateAge(registrationParameter.getAge());
        validateEmail(registrationParameter.getMail());
        validatePassword(registrationParameter.getPassword());
        validateEmailExistence(registrationParameter.getMail());
    }

    public void validateOnUpdate(UUID userId, UserUpdateParameter userUpdateParameter) {
        validateAge(userUpdateParameter.getAge());
        validateEmail(userUpdateParameter.getMail());
        validatePassword(userUpdateParameter.getPassword());
        validateEmailExistence(userId, userUpdateParameter.getMail());
    }

    private void validateAge(int age) {
        if (age < 10 || age > 100) {
            throw new UserInvalidAgeException();
        }
    }

    private void validateEmail(String mail) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(mail);

        if(!matcher.matches()) {
            throw new UserInvalidEmailException();
        }
    }

    private void validatePassword(String password){
        String regexPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);

        if(!matcher.matches()) {
            throw new UserInvalidPasswordException();
        }
    }

    private void validateEmailExistence(String mail) {
        if(userJpaRepository.existsByMail(mail)){
            throw new UserExistingEmailException();
        }
    }

    private void validateEmailExistence(UUID userId, String mail) {
        if(userJpaRepository.existsByMailAndIdNot(mail, userId)){
            throw new UserExistingEmailException();
        }
    }

}
