package com.example.survey.model.type_of_question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class DateQuestionDto implements TypeOfQuestionDto{
    private String question;
    private boolean isRequired = false;
}
