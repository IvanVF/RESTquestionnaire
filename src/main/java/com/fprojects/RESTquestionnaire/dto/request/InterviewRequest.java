package com.fprojects.RESTquestionnaire.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Запрос на создание опроса
 */
@Data
public class InterviewRequest {

    @Size(min = 2, message = "Название опроса 'interviewName' должно быть не меньше 2х символов")
    private String interviewName;

    @NotNull(message = "Должна быть указана дата начала опроса")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotNull(message = "Должна быть указана дата окончания опроса")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private boolean active;

}
