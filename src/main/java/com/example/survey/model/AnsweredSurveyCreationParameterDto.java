package com.example.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AnsweredSurveyCreationParameterDto {

    private UUID userId;
    private UUID surveyId;
    private List<String> answers;
}
