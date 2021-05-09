package com.fprojects.RESTquestionnaire.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class ErrorHandler {

    private static ResponseEntity<Object> generateDefaultExceptionResponse(String message, List<String> description, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(message);
        exceptionResponse.setHttpCode(httpStatus.value());
        exceptionResponse.setDescription(description);
        return ResponseEntity.status(exceptionResponse.getHttpCode()).body(exceptionResponse);
    }

    @ExceptionHandler({SQLException.class})
    private static ResponseEntity<Object> handleSQLException(SQLException exception) {
        List<String> description = new ArrayList<>();
        description.add(exception.getMessage());
        return generateDefaultExceptionResponse("Ошибка SQL запроса", description, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    private static ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        List<String> description = new ArrayList<>();
        description.add(exception.getMessage());
        return generateDefaultExceptionResponse("Ошибка формата данных", description, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    private static ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        List<String> description = new ArrayList<>();
        description.add(exception.getMessage());
        return generateDefaultExceptionResponse("Ошибка валидации", description, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    private static ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> description = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            description.add(error.getField() + " " + error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            description.add(" " + error.getDefaultMessage());
        }
        return generateDefaultExceptionResponse("Ошибка валидации", description, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    private static ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        List<String> description = new ArrayList<>();
        description.add(exception.getMessage());
        return generateDefaultExceptionResponse("Ресурс с таким id не найден", description, HttpStatus.NOT_FOUND);
    }

}
