package com.example.survey.model;

import com.example.survey.model.typy_of_answer.TypeOfAnswerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnsweredSurveyDto {
    private UserDto user;
    private SurveyDto survey;
    private List<TypeOfAnswerDto> answers = new ArrayList<>();
}
