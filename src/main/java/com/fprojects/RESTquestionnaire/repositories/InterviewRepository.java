package com.fprojects.RESTquestionnaire.repositories;

import com.fprojects.RESTquestionnaire.entities.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий опроса
 */
@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, UUID> {
}
