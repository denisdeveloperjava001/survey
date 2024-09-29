package com.example.survey.model.typy_of_answer;

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

public class ManyVariantAnswer implements TypeOfAnswer {
    private List<String> variants = new ArrayList<>();
}
