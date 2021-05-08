package com.fprojects.RESTquestionnaire.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "question")
    @Size(min = 2, message = "Минимум 2 символа")
    private String question;

    @Column(name = "order")
    @NotNull
    @Positive
    private int order;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private InterviewEntity interviewEntity;

}
