package com.fprojects.RESTquestionnaire.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Ответ список опросов
 */
@Data
public class InterviewResponse {

    private UUID id;

    private String interviewName;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;
}
