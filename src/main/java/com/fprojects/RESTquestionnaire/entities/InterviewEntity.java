package com.fprojects.RESTquestionnaire.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "interviews")
@Data
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;

    @Column(name = "interview_name")
    @Size(min = 2, message = "Минимум 2 символа")
    String interviewName;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;

    @Column(name = "active")
    boolean active;

    @OneToMany(mappedBy = "interviewEntity")
    private List<QuestionEntity> questionEntityList;
}
