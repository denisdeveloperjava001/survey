package com.example.survey.model;

import com.example.survey.model.typy_of_answer.TypeOfAnswerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AnsweredSurveyCreationParameterDto {

    private UUID userId;
    private UUID surveyId;
    private List<TypeOfAnswerDto> answers = new ArrayList<>();
}
