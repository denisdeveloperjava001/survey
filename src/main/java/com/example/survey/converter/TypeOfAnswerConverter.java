package com.example.survey.converter;

import com.example.survey.model.type_of_question.DateQuestionDto;
import com.example.survey.model.typy_of_answer.*;

import java.util.Date;

public class TypeOfAnswerConverter {

    public static TypeOfAnswer toEntity (TypeOfAnswerDto typeOfAnswerDto){

        if(typeOfAnswerDto instanceof DateAnswerDto dateAnswerDto){
            DateAnswer dateAnswer = new DateAnswer();
            dateAnswer.setDate(dateAnswerDto.getDate());
            return dateAnswer;
        }

        if(typeOfAnswerDto instanceof StringAnswerDto stringAnswerDto){
            StringAnswer stringAnswer = new StringAnswer();
            stringAnswer.setString(stringAnswerDto.getString());
            return stringAnswer;
        }

        if(typeOfAnswerDto instanceof OneVariantAnswerDto oneVariantAnswerDto){
            OneVariantAnswer oneVariantAnswer = new OneVariantAnswer();
            oneVariantAnswer.setString(oneVariantAnswerDto.getString());
            return oneVariantAnswer;
        }

        if(typeOfAnswerDto instanceof ManyVariantAnswerDto manyVariantAnswerDto){
            ManyVariantAnswer manyVariantAnswer = new ManyVariantAnswer();
            manyVariantAnswer.getVariants().addAll(manyVariantAnswerDto.getVariants());
            return manyVariantAnswer;
        }

        throw new RuntimeException("Невозможно конвертировать,неизвестный тип");
    }

    public static TypeOfAnswerDto toDto (TypeOfAnswer typeOfAnswer){

        if(typeOfAnswer instanceof DateAnswer dateAnswer){
            DateAnswerDto dateAnswerDto = new DateAnswerDto();
            dateAnswerDto.setDate(dateAnswer.getDate());
            return dateAnswerDto;
        }

        if(typeOfAnswer instanceof StringAnswer stringAnswer){
            StringAnswerDto stringAnswerDto = new StringAnswerDto();
            stringAnswerDto.setString(stringAnswer.getString());
            return stringAnswerDto;
        }

        if(typeOfAnswer instanceof OneVariantAnswer oneVariantAnswer){
            OneVariantAnswerDto oneVariantAnswerDto = new OneVariantAnswerDto();
            oneVariantAnswerDto.setString(oneVariantAnswer.getString());
            return oneVariantAnswerDto;
        }

        if(typeOfAnswer instanceof ManyVariantAnswer manyVariantAnswer){
            ManyVariantAnswerDto manyVariantAnswerDto = new ManyVariantAnswerDto();
            manyVariantAnswerDto.getVariants().addAll(manyVariantAnswer.getVariants());
            return manyVariantAnswerDto;
        }

        throw new RuntimeException("Невозможно конвертировать,неизвестный тип");
    }
}
