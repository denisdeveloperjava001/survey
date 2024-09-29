package com.example.survey.model.type_of_question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = MultipleAnswersManyVariantsQuestion.class, name = "ManyVariantQuestion"),
        @JsonSubTypes.Type(value = MultipleAnswersOneVariantQuestion.class, name = "OneVariantQuestion"),
        @JsonSubTypes.Type(value = StringQuestion.class, name = "StringQuestion"),
        @JsonSubTypes.Type(value = DateQuestion.class, name = "DateQuestion")
})
public interface TypeOfQuestion {
}
