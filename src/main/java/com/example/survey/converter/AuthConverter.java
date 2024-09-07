package com.example.survey.converter;

import com.example.survey.model.*;

public class AuthConverter {

    public static RegistrationResponseDto toDto (RegistrationResponse registrationResponse){

        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();
        registrationResponseDto.setUserDto(registrationResponse.getUserDto());
        registrationResponseDto.setToken(registrationResponse.getToken());

        return registrationResponseDto;
    }

    public static RegistrationParameter toEntity (RegistrationParameterDto registrationParameterDto){
        RegistrationParameter registrationParameter = new RegistrationParameter();
        registrationParameter.setAge(registrationParameterDto.getAge());
        registrationParameter.setGender(registrationParameterDto.getGender());
        registrationParameter.setMail(registrationParameterDto.getMail());
        registrationParameter.setName(registrationParameterDto.getName());
        registrationParameter.setPassword(registrationParameterDto.getPassword());

        return registrationParameter;
    }


    public static SignInParameter toEntity (SignInParameterDto signInParameterDto){

        SignInParameter signInParameter = new SignInParameter();
        signInParameter.setPassword(signInParameterDto.getPassword());
        signInParameter.setMail(signInParameterDto.getMail());

        return signInParameter;
    }

    public static SingInResponseDto toDto (SingInResponse singInResponse){

        SingInResponseDto singInResponseDto = new SingInResponseDto();
        singInResponseDto.setUserDto(singInResponse.getUserDto());
        singInResponseDto.setToken(singInResponse.getToken());

        return singInResponseDto;
    }


}
