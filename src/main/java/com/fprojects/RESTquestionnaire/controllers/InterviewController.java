package com.fprojects.RESTquestionnaire.controllers;

import com.fprojects.RESTquestionnaire.dto.request.InterviewEditRequest;
import com.fprojects.RESTquestionnaire.dto.request.InterviewRequest;
import com.fprojects.RESTquestionnaire.entities.InterviewEntity;
import com.fprojects.RESTquestionnaire.enums.SortingType;
import com.fprojects.RESTquestionnaire.exceptions.ResourceNotFoundException;
import com.fprojects.RESTquestionnaire.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Контроллер опроса
 */
@RestController
@Validated
public class InterviewController {

    private final InterviewService interviewService;

    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    /**
     * Создать опрос
     */
    @PostMapping("/interview")
    public void createInterview(@Validated @RequestBody InterviewRequest interviewRequest) throws HttpMessageNotReadableException {
        interviewService.createInterview(interviewRequest);
    }

    /**
     * Получить все опросы
     */
    @GetMapping("/interview")
    public List<InterviewEntity> getAllInterviews(
            @RequestParam(value = "sort", required = false, defaultValue = "DATE") SortingType sort,
            @RequestParam(required = false) @DateTimeFormat(pattern = "d-MM-yyyy") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "d-MM-yyyy") LocalDate endDate,
            @RequestParam(required = false) String interviewName,
            @RequestParam(required = false) String active) throws NullPointerException {
        return interviewService.getAllInterviews(sort, startDate, endDate, interviewName, active);
    }

    /**
     * Получить опрос по id
     */
    @GetMapping("/interview/{id}")
    public InterviewEntity getInterview(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        return interviewService.getInterview(id);
    }

    /**
     * Изменить опрос по id
     */
    @PutMapping("/interview/{id}")
    public void editInterview(@Validated @PathVariable("id") UUID id, @RequestBody InterviewEditRequest interviewEditRequest) throws ResourceNotFoundException {
        interviewService.editInterview(id, interviewEditRequest);
    }

    /**
     * Удалить опрос по id
     */
    @DeleteMapping("/interview/{id}")
    public void deleteInterview(@PathVariable("id") UUID id) {
        interviewService.deleteInterview(id);
    }



}
