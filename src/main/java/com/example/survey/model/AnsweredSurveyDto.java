package com.example.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AnsweredSurveyDto {
    private UserDto user;
    private SurveyDto survey;
    private List<String> answers;
}
