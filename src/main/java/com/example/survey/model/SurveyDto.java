package com.example.survey.model;

import com.example.survey.model.type_of_question.TypeOfQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    private UUID id;
    private UserDto owner;
    private String title;
    private List<TypeOfQuestionDto> questions = new ArrayList<>();
    private OffsetDateTime creationDate;
    private long respondentsCount;
}