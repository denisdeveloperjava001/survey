package com.example.survey.model;

import com.example.survey.model.type_of_question.TypeOfQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private String title;

    /** @noinspection JpaAttributeTypeInspection*/
    @JdbcTypeCode(SqlTypes.JSON)
    private List<TypeOfQuestion> questions = new ArrayList<>();

    private OffsetDateTime creationDate;

    private long respondentsCount;



}
