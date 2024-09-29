package com.example.survey.model.typy_of_answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class StringAnswer implements TypeOfAnswer {
    private String string;
}
