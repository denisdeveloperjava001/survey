package com.example.survey.model.typy_of_answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = ManyVariantAnswerDto.class, name = "ManyVariantAnswer"),
        @JsonSubTypes.Type(value = OneVariantAnswerDto.class, name = "OneVariantAnswer"),
        @JsonSubTypes.Type(value = StringAnswerDto.class, name = "StringAnswer"),
        @JsonSubTypes.Type(value = DateAnswerDto.class, name = "DateAnswer")
})


public interface TypeOfAnswerDto {
}
