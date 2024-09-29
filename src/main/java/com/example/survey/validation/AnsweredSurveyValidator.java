package com.example.survey.validation;

import com.example.survey.model.AnsweredSurvey;
import com.example.survey.model.AnsweredSurveyCreateParameter;
import com.example.survey.model.Survey;
import com.example.survey.model.type_of_question.*;
import com.example.survey.model.typy_of_answer.*;
import com.example.survey.repository.SurveyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AnsweredSurveyValidator {

    @Autowired
    SurveyJpaRepository surveyJpaRepository;


    public void validateOnCreation (AnsweredSurveyCreateParameter answeredSurveyCreateParameter){
        Survey survey = surveyJpaRepository.findById(answeredSurveyCreateParameter.getSurveyId()).get();

        if(answeredSurveyCreateParameter.getAnswers().size() != survey.getQuestions().size()) {
            throw new RuntimeException("не на все вопросы ответил");

        }
    }

    public void validateOnType (AnsweredSurveyCreateParameter answeredSurveyCreateParameter){
        Survey survey = surveyJpaRepository.findById(answeredSurveyCreateParameter.getSurveyId()).get();

        for(int i = 0; i < survey.getQuestions().size(); i++){
            TypeOfQuestion question = survey.getQuestions().get(i);
            TypeOfAnswer answer = answeredSurveyCreateParameter.getAnswers().get(i);

            if(question instanceof DateQuestion){
                if(!(answer instanceof DateAnswer)){
                    throw new RuntimeException("ответ №" + i+1 + " не соответствует типу вопроса");
                }
            } else if(question instanceof StringQuestion){
                if(!(answer instanceof StringAnswer)){
                    throw new RuntimeException("ответ №" + i+1 + " не соответствует типу вопроса");
                }
            } else if(question instanceof MultipleAnswersManyVariantsQuestion){
                if(!(answer instanceof ManyVariantAnswer)){
                    throw new RuntimeException("ответ №" + i+1 + " не соответствует типу вопроса");
                }
            } else if(question instanceof MultipleAnswersOneVariantQuestion){
                if(!(answer instanceof OneVariantAnswer)){
                    throw new RuntimeException("ответ №" + i+1 + " не соответствует типу вопроса");
                }
            }else {
                throw new RuntimeException("ответ №" + i+1 + " не соответствует типу вопроса");
            }

        }
    }

    public void validateVariant (AnsweredSurveyCreateParameter answeredSurveyCreateParameter){
        Survey survey = surveyJpaRepository.findById(answeredSurveyCreateParameter.getSurveyId()).get();
        for(int i = 0; i < answeredSurveyCreateParameter.getAnswers().size(); i++){
            TypeOfAnswer answer = answeredSurveyCreateParameter.getAnswers().get(i);
            TypeOfQuestion question = survey.getQuestions().get(i);

            if(answer instanceof OneVariantAnswer oneVariantAnswer){
                MultipleAnswersOneVariantQuestion oneVariantQuestion = (MultipleAnswersOneVariantQuestion) question;
                boolean match = oneVariantQuestion.getVariants().contains(oneVariantAnswer.getString());
                if(!match){
                    throw new RuntimeException("Некорректный ответ в вопросе №"+ (i+1));
                }
            } else if(answer instanceof ManyVariantAnswer manyVariantAnswer){
                MultipleAnswersManyVariantsQuestion manyVariantsQuestion = (MultipleAnswersManyVariantsQuestion) question;
                boolean match = new HashSet<>(manyVariantsQuestion.getVariants()).containsAll(manyVariantAnswer.getVariants());
                if(!match){
                    throw new RuntimeException("Некорректный ответв вопросе №"+ (i+1));
                }
            }
        }
    }

    public void validateRequiredQuestion (AnsweredSurveyCreateParameter answeredSurveyCreateParameter){
        Survey survey = surveyJpaRepository.findById(answeredSurveyCreateParameter.getSurveyId()).get();
        for(int i = 0; i < survey.getQuestions().size(); i++){
            TypeOfQuestion question = survey.getQuestions().get(i);
            TypeOfAnswer answer = answeredSurveyCreateParameter.getAnswers().get(i);


            if(question instanceof DateQuestion dateQuestion){
                if(dateQuestion.isRequired()){
                    if(((DateAnswer) answer).getDate() == null){
                        throw new RuntimeException("ответ №" + (i+1) + " является обязательным");
                    }
                }
            } else if(question instanceof StringQuestion stringQuestion){
                if(stringQuestion.isRequired()){
                    if(((StringAnswer) answer).getString() == null){
                        throw new RuntimeException("ответ №" + (i+1) + " является обязательным");
                    }
                }
            } else if(question instanceof MultipleAnswersManyVariantsQuestion multipleAnswersManyVariantsQuestion){
                if(multipleAnswersManyVariantsQuestion.isRequired()){
                    if(((ManyVariantAnswer) answer).getVariants().isEmpty()){
                        throw new RuntimeException("ответ №" + (i+1) + " является обязательным");
                    }
                }
            } else if(question instanceof MultipleAnswersOneVariantQuestion multipleAnswersOneVariantQuestion){
                if(multipleAnswersOneVariantQuestion.isRequired()){
                    if(((OneVariantAnswer) answer).getString() == null){
                        throw new RuntimeException("ответ №" + (i+1) + " является обязательным");
                    }
                }
            }else {
                throw new RuntimeException("ответ №" + (i+1) + " не соответствует типу вопроса");
            }

        }

    }
}
