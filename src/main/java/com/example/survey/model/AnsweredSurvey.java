package com.example.survey.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answered_survey")
@IdClass(AnsweredSurvey.CompositeKey.class)
public class AnsweredSurvey {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    /** @noinspection JpaAttributeTypeInspection*/
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> answers;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class CompositeKey implements Serializable {
        private UUID user;
        private UUID survey;
    }

}
