package com.fprojects.RESTquestionnaire.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Сущность вопроса
 */
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

    @Column(name = "q_order")
    @NotNull
    @Positive(message = "Номер вопроса должен быть положительным числом")
    private int qorder;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private InterviewEntity interviewEntity;

}
