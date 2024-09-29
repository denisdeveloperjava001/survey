package com.example.survey.model.type_of_question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = MultipleAnswersManyVariantsQuestionDto.class, name = "ManyVariantQuestion"),
        @JsonSubTypes.Type(value = MultipleAnswersOneVariantQuestionDto.class, name = "OneVariantQuestion"),
        @JsonSubTypes.Type(value = StringQuestionDto.class, name = "StringQuestion"),
        @JsonSubTypes.Type(value = DateQuestionDto.class, name = "DateQuestion")
})
public interface TypeOfQuestionDto {
}
