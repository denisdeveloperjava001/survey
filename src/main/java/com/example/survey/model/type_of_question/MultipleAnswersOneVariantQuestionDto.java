package com.example.survey.model.type_of_question;

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

public class MultipleAnswersOneVariantQuestionDto implements TypeOfQuestionDto{
    private String question;
    private List<String> variants = new ArrayList<>();
    private boolean isRequired = false;
}
