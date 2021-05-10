package com.fprojects.RESTquestionnaire.controllers;

import com.fprojects.RESTquestionnaire.dto.request.QuestionEditRequest;
import com.fprojects.RESTquestionnaire.dto.request.QuestionRequest;
import com.fprojects.RESTquestionnaire.dto.request.QuestionRequestList;
import com.fprojects.RESTquestionnaire.entities.QuestionEntity;
import com.fprojects.RESTquestionnaire.exceptions.ResourceNotFoundException;
import com.fprojects.RESTquestionnaire.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер вопросов
 */
@RestController
@Validated
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Создать вопросы к опросу
     */
    @PostMapping("/question/{interviewId}")
    public void createQuestions(@PathVariable("interviewId") UUID interviewId,
                                @Validated @RequestBody QuestionRequestList questionRequests) throws ResourceNotFoundException {
        questionService.createQuestion(interviewId, questionRequests);
    }

    /**
     *Получить все вопросы
     */
    @GetMapping("question")
    public List<QuestionEntity> getAllQuestions() { return questionService.getAllQuestions(); }

    /**
     *Изменить вопрос по id вопроса
     */
    @PutMapping("/question/{questionId}")
    public void editQuestion(@PathVariable("questionId") UUID questionId,
                             @RequestBody QuestionEditRequest request) throws ResourceNotFoundException {
        questionService.editQuestion(questionId, request);
    }

    /**
     *Удалить вопрос по id
     */
    @DeleteMapping("/question/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") UUID questionId) { questionService.deleteQuestion(questionId); }

}
