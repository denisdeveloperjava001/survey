package com.example.survey.converter;

import com.example.survey.model.type_of_question.*;

public class TypeOfQuestionConverter {

    public static TypeOfQuestion toEntity(TypeOfQuestionDto typeOfQuestionDto) {
       if(typeOfQuestionDto instanceof DateQuestionDto dateQuestionDto) {
           DateQuestion dateQuestion = new DateQuestion();
           dateQuestion.setQuestion(dateQuestionDto.getQuestion());
           dateQuestion.setRequired(dateQuestionDto.isRequired());
           return dateQuestion;
       }

       if(typeOfQuestionDto instanceof StringQuestionDto stringQuestionDto) {
           StringQuestion stringQuestion = new StringQuestion();
           stringQuestion.setQuestion(stringQuestionDto.getQuestion());
           stringQuestion.setRequired(stringQuestionDto.isRequired());
           return stringQuestion;
       }

       if(typeOfQuestionDto instanceof MultipleAnswersOneVariantQuestionDto multipleAnswersOneVariantQuestionDto) {
           MultipleAnswersOneVariantQuestion multipleAnswersOneVariantQuestion = new MultipleAnswersOneVariantQuestion();
           multipleAnswersOneVariantQuestion.setQuestion(multipleAnswersOneVariantQuestionDto.getQuestion());
           multipleAnswersOneVariantQuestion.getVariants().addAll(multipleAnswersOneVariantQuestionDto.getVariants());
           multipleAnswersOneVariantQuestion.setRequired(multipleAnswersOneVariantQuestionDto.isRequired());
           return multipleAnswersOneVariantQuestion;
       }

       if(typeOfQuestionDto instanceof MultipleAnswersManyVariantsQuestionDto multipleAnswersManyVariantsQuestionDto) {
           MultipleAnswersManyVariantsQuestion multipleAnswersManyVariantsQuestion = new MultipleAnswersManyVariantsQuestion();
           multipleAnswersManyVariantsQuestion.setQuestion(multipleAnswersManyVariantsQuestionDto.getQuestion());
           multipleAnswersManyVariantsQuestion.getVariants().addAll(multipleAnswersManyVariantsQuestionDto.getVariants());
           multipleAnswersManyVariantsQuestion.setRequired(multipleAnswersManyVariantsQuestionDto.isRequired());
           return multipleAnswersManyVariantsQuestion;
       }
       throw new RuntimeException("Невозможно конвертировать,неизвестный тип");
    }

    public static TypeOfQuestionDto toDto(TypeOfQuestion typeOfQuestion) {
        if(typeOfQuestion instanceof DateQuestion dateQuestion) {
            DateQuestionDto dateQuestionDto = new DateQuestionDto();
            dateQuestionDto.setQuestion(dateQuestion.getQuestion());
            dateQuestionDto.setRequired(dateQuestion.isRequired());
            return dateQuestionDto;
        }

        if(typeOfQuestion instanceof StringQuestion stringQuestion) {
            StringQuestionDto stringQuestionDto = new StringQuestionDto();
            stringQuestionDto.setQuestion(stringQuestion.getQuestion());
            stringQuestionDto.setRequired(stringQuestion.isRequired());
            return stringQuestionDto;
        }

        if(typeOfQuestion instanceof MultipleAnswersOneVariantQuestion multipleAnswersOneVariantQuestion) {
            MultipleAnswersOneVariantQuestionDto multipleAnswersOneVariantQuestionDto = new MultipleAnswersOneVariantQuestionDto();
            multipleAnswersOneVariantQuestionDto.setQuestion(multipleAnswersOneVariantQuestion.getQuestion());
            multipleAnswersOneVariantQuestionDto.getVariants().addAll(multipleAnswersOneVariantQuestion.getVariants());
            multipleAnswersOneVariantQuestionDto.setRequired(multipleAnswersOneVariantQuestion.isRequired());
            return multipleAnswersOneVariantQuestionDto;
        }

        if(typeOfQuestion instanceof MultipleAnswersManyVariantsQuestion multipleAnswersManyVariantsQuestion) {
            MultipleAnswersManyVariantsQuestionDto multipleAnswersManyVariantsQuestionDto = new MultipleAnswersManyVariantsQuestionDto();
            multipleAnswersManyVariantsQuestionDto.setQuestion(multipleAnswersManyVariantsQuestion.getQuestion());
            multipleAnswersManyVariantsQuestionDto.getVariants().addAll(multipleAnswersManyVariantsQuestion.getVariants());
            multipleAnswersManyVariantsQuestionDto.setRequired(multipleAnswersManyVariantsQuestion.isRequired());
            return multipleAnswersManyVariantsQuestionDto;
        }

        throw new RuntimeException("Невозможно конвертировать,неизвестный тип");
    }

}

