package com.example.survey.model.typy_of_answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class DateAnswer implements TypeOfAnswer{
    private Date date;
}
