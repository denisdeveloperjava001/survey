package com.example.survey.converter;

import com.example.survey.model.User;
import com.example.survey.model.UserDto;
import com.example.survey.model.UserUpdateParameter;
import com.example.survey.model.UserUpdateParameterDto;

public class UserConverter {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setGender(user.getGender());
        dto.setCreationDate(user.getCreationDate());
        dto.setMail(user.getMail());
        return dto;
    }

    public static UserUpdateParameter toEntity(UserUpdateParameterDto dto) {
        UserUpdateParameter entity = new UserUpdateParameter();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setMail(dto.getMail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

}
