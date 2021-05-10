package com.fprojects.RESTquestionnaire.services;

import com.fprojects.RESTquestionnaire.dto.request.QuestionEditRequest;
import com.fprojects.RESTquestionnaire.dto.request.QuestionRequest;
import com.fprojects.RESTquestionnaire.dto.request.QuestionRequestList;
import com.fprojects.RESTquestionnaire.entities.InterviewEntity;
import com.fprojects.RESTquestionnaire.entities.QuestionEntity;
import com.fprojects.RESTquestionnaire.exceptions.ResourceNotFoundException;
import com.fprojects.RESTquestionnaire.repositories.InterviewRepository;
import com.fprojects.RESTquestionnaire.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Сервис вопросов
 */
@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final InterviewRepository interviewRepository;

    /**
     * Конструктор
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository, InterviewRepository interviewRepository) {
        this.questionRepository = questionRepository;
        this.interviewRepository = interviewRepository;
    }

    /**
     * Добавить список вопросов в опрос
     */
    public void createQuestion(UUID interviewId, QuestionRequestList questionRequests) throws ResourceNotFoundException {
        InterviewEntity interviewEntity = interviewRepository.findById(interviewId).orElseThrow(() -> new ResourceNotFoundException());

        for (QuestionRequest questionRequest : questionRequests.getQuestionRequestList()) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setInterviewEntity(interviewEntity);
            questionEntity.setQuestion(questionRequest.getQuestion());
            questionEntity.setQorder(questionRequest.getQorder());
            questionRepository.save(questionEntity);
        }
    }

    /**
     *Получить все вопросы
     */
    public List<QuestionEntity> getAllQuestions() {
        Sort sort = Sort.by("interviewEntity").ascending().and(Sort.by("qorder").ascending());
        return questionRepository.findAll(sort);
    }

    /**
     *Изменить вопрос id вопроса
     */
    public void editQuestion(UUID id, QuestionEditRequest request) throws ResourceNotFoundException {
        QuestionEntity entity = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());

        if (request.getQuestion() != null) { entity.setQuestion(request.getQuestion()); }
        if (request.getQorder() != 0) {entity.setQorder(request.getQorder());}

        questionRepository.save(entity);
    }

    /**
     *Удалить вопрос по id
     */
    public void deleteQuestion(UUID id) { questionRepository.deleteById(id); }
}
