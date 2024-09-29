package com.example.survey.model.typy_of_answer;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = ManyVariantAnswer.class, name = "ManyVariantAnswer"),
        @JsonSubTypes.Type(value = OneVariantAnswer.class, name = "OneVariantAnswer"),
        @JsonSubTypes.Type(value = StringAnswer.class, name = "StringAnswer"),
        @JsonSubTypes.Type(value = DateAnswer.class, name = "DateAnswer")
})

public interface TypeOfAnswer {
}
