package com.fprojects.RESTquestionnaire.repositories;

import com.fprojects.RESTquestionnaire.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий вопросов
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
}
