package com.fprojects.RESTquestionnaire.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * Запрос на обновление опроса
 */
@Data
public class InterviewEditRequest {

    private String interviewName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private String active;

}
