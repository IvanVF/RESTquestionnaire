package com.fprojects.RESTquestionnaire.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Запрос на создание вопроса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    @Size(min = 2, message = "Минимум 2 символа")
    private String question;

    @NotNull
    @Positive
    private int qorder;
}
