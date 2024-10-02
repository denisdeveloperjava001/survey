package com.example.survey.validation;

import com.example.survey.exception.*;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.model.type_of_question.*;
import com.example.survey.model.typy_of_answer.*;
import com.example.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AnsweredSurveyValidator {

    @Autowired
    private SurveyService surveyService;

    public void validateOnCreation(AnsweredSurveyCreateParameter createParameter) {
        Survey survey = surveyService.getOrThrow(createParameter.getSurveyId());

        validateSize(createParameter, survey);
        validateType(createParameter, survey);
        validateRequiredQuestion(createParameter, survey);
        validateVariant(createParameter, survey);
    }

    private void validateSize(AnsweredSurveyCreateParameter createParameter, Survey survey) {
        if(createParameter.getAnswers().size() != survey.getQuestions().size()) {
            throw new AnsweredSurveyAnswerSizeException();
        }
    }

    private void validateType(AnsweredSurveyCreateParameter answeredSurveyCreateParameter, Survey survey) {
        for(int i = 0; i < survey.getQuestions().size(); i++) {
            TypeOfQuestion question = survey.getQuestions().get(i);
            TypeOfAnswer answer = answeredSurveyCreateParameter.getAnswers().get(i);

            if(question instanceof DateQuestion) {
                if(!(answer instanceof DateAnswer)){
                    throw new AnsweredSurveyInvalidAnswerTypeException(i + 1);
                }
            } else if(question instanceof StringQuestion) {
                if(!(answer instanceof StringAnswer)) {
                    throw new AnsweredSurveyInvalidAnswerTypeException(i + 1);
                }
            } else if(question instanceof MultipleAnswersManyVariantsQuestion) {
                if(!(answer instanceof ManyVariantAnswer)) {
                    throw new AnsweredSurveyInvalidAnswerTypeException(i + 1);
                }
            } else if(question instanceof MultipleAnswersOneVariantQuestion) {
                if(!(answer instanceof OneVariantAnswer)) {
                    throw new AnsweredSurveyInvalidAnswerTypeException(i + 1);
                }
            } else {
                throw new UnknownTypeException();
            }
        }
    }

    private void validateVariant(AnsweredSurveyCreateParameter answeredSurveyCreateParameter, Survey survey) {
        for(int i = 0; i < answeredSurveyCreateParameter.getAnswers().size(); i++) {
            TypeOfAnswer answer = answeredSurveyCreateParameter.getAnswers().get(i);
            TypeOfQuestion question = survey.getQuestions().get(i);

            if(answer instanceof OneVariantAnswer oneVariantAnswer) {
                MultipleAnswersOneVariantQuestion oneVariantQuestion = (MultipleAnswersOneVariantQuestion) question;
                boolean match = oneVariantQuestion.getVariants().contains(oneVariantAnswer.getString());
                if(!match) {
                    throw new AnsweredSurveyInvalidVariantException(i + 1);
                }
            } else if(answer instanceof ManyVariantAnswer manyVariantAnswer) {
                MultipleAnswersManyVariantsQuestion manyVariantsQuestion = (MultipleAnswersManyVariantsQuestion) question;
                boolean match = new HashSet<>(manyVariantsQuestion.getVariants()).containsAll(manyVariantAnswer.getVariants());
                if(!match) {
                    throw new AnsweredSurveyInvalidVariantException(i + 1);
                }
            }
        }
    }

    private void validateRequiredQuestion(AnsweredSurveyCreateParameter answeredSurveyCreateParameter, Survey survey) {
        for(int i = 0; i < survey.getQuestions().size(); i++) {
            TypeOfQuestion question = survey.getQuestions().get(i);
            TypeOfAnswer answer = answeredSurveyCreateParameter.getAnswers().get(i);

            if(question instanceof DateQuestion dateQuestion) {
                if(dateQuestion.isRequired()){
                    if(((DateAnswer) answer).getDate() == null) {
                        throw new AnsweredSurveyRequiredAnswerException(i + 1);
                    }
                }
            } else if(question instanceof StringQuestion stringQuestion) {
                if(stringQuestion.isRequired()) {
                    if(((StringAnswer) answer).getString() == null) {
                        throw new AnsweredSurveyRequiredAnswerException(i + 1);
                    }
                }
            } else if(question instanceof MultipleAnswersManyVariantsQuestion multipleAnswersManyVariantsQuestion) {
                if(multipleAnswersManyVariantsQuestion.isRequired()) {
                    if(((ManyVariantAnswer) answer).getVariants().isEmpty()) {
                        throw new AnsweredSurveyRequiredAnswerException(i + 1);
                    }
                }
            } else if(question instanceof MultipleAnswersOneVariantQuestion multipleAnswersOneVariantQuestion) {
                if(multipleAnswersOneVariantQuestion.isRequired()) {
                    if(((OneVariantAnswer) answer).getString() == null) {
                        throw new AnsweredSurveyRequiredAnswerException(i + 1);
                    }
                }
            } else {
                throw new UnknownTypeException();
            }
        }
    }

}
