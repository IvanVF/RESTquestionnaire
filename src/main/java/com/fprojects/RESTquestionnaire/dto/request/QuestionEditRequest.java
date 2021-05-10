package com.fprojects.RESTquestionnaire.dto.request;

import lombok.Data;

/**
 * Запрос на изменение вопроса
 */
@Data
public class QuestionEditRequest {

    private String question;

    private int qorder;

}
