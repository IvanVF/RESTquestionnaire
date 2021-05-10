package com.fprojects.RESTquestionnaire.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class QuestionRequestList {
    private List<QuestionRequest> questionRequestList;
}
