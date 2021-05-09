package com.fprojects.RESTquestionnaire.exceptions;

import lombok.Data;

import java.util.List;

/**
 * Ответ на ошибки-исключения
 */
@Data
public class ExceptionResponse {

    private int httpCode;

    private String message;

    private List<String> description;

}
