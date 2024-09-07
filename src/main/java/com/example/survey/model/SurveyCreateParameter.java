package com.example.survey.model;

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
public class SurveyCreateParameter {

    private UUID ownerId;

    private String title;

    private ArrayList<String> questions = new ArrayList<>();
}
