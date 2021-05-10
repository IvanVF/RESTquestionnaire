package com.fprojects.RESTquestionnaire.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.aspectj.lang.annotation.After;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Сущность опроса
 */
@Entity
@Table(name = "interviews")
@Data
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "interview_name")
    @Size(min = 2, message = "Название опроса 'interviewName' должно быть не меньше 2х символов")
    private String interviewName;

    @Column(name = "start_date")
    @NotNull(message = "Должна быть указана дата начала опроса")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull(message = "Должна быть указана дата окончания опроса")
    private LocalDate endDate;

    @Column(name = "active")
    @NotNull
    private boolean active;

    @OneToMany(mappedBy = "interviewEntity")
    @JsonManagedReference
    private List<QuestionEntity> questionEntityList;
}
