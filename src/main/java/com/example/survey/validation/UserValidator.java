package com.example.survey.validation;

import com.example.survey.model.RegistrationParameter;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidator {

    public void validateOnCreation(RegistrationParameter registrationParameter){
        ageValidation(registrationParameter);
        emailValidation(registrationParameter);
        passwordValidation(registrationParameter);

    }

    private void ageValidation (RegistrationParameter registrationParameter){
        int age = registrationParameter.getAge();

        if (age < 10 || age > 100) {
            throw new RuntimeException("не соответствует диапазону допустимого возраста");
        }
    }

    private void emailValidation(RegistrationParameter registrationParameter) {
        String mail = registrationParameter.getMail();
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(mail);
        if(!matcher.matches()){
            throw new RuntimeException("емэйл не соответствует требованиям");
        }
    }

    private void passwordValidation(RegistrationParameter registrationParameter){
        String password = registrationParameter.getPassword();

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



}
