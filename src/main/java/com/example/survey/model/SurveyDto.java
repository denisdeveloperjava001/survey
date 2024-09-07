package com.example.survey.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    private List<String> questions = new ArrayList<>();
    private OffsetDateTime creationDate;
    private long respondentsCount;

}