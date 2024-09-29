package com.example.survey.model;

import com.example.survey.model.type_of_question.TypeOfQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateParameterDto {

    private UUID ownerId;

    private String title;

    private ArrayList<TypeOfQuestionDto> questions;
}
