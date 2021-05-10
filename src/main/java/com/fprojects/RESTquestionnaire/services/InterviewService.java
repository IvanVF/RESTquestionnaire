package com.fprojects.RESTquestionnaire.services;

import com.fprojects.RESTquestionnaire.dto.request.InterviewEditRequest;
import com.fprojects.RESTquestionnaire.dto.request.InterviewRequest;
import com.fprojects.RESTquestionnaire.entities.InterviewEntity;
import com.fprojects.RESTquestionnaire.enums.SortingType;
import com.fprojects.RESTquestionnaire.exceptions.ResourceNotFoundException;
import com.fprojects.RESTquestionnaire.repositories.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис опроса
 */
@Service
@Transactional
public class InterviewService {

    private final InterviewRepository interviewRepository;

    /**
     * Конструктор
     */
    @Autowired
    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    /**
     * Создать опрос
     */
    public void createInterview(InterviewRequest interviewRequest) {
        InterviewEntity interviewEntity = new InterviewEntity();
        interviewEntity.setId(UUID.randomUUID());
        interviewEntity.setInterviewName(interviewRequest.getInterviewName());
        interviewEntity.setStartDate(interviewRequest.getStartDate());
        interviewEntity.setEndDate(interviewRequest.getEndDate());
        interviewEntity.setActive(interviewRequest.isActive());

        interviewRepository.save(interviewEntity);
    }

    /**
     * Получить все опросы
     */
    public List<InterviewEntity> getAllInterviews(SortingType sortingType,
                                                  LocalDate startDate,
                                                  LocalDate endDate,
                                                  String interviewName,
                                                  String active) {
        Sort sort;
        if (sortingType == SortingType.DATE) {
            sort = Sort.by("startDate").descending().and(Sort.by("interviewName").ascending());
        } else {
            sort = Sort.by("interviewName").ascending().and(Sort.by("startDate").descending());
        }
        List<InterviewEntity> interviewEntityList = interviewRepository.findAll(sort);

        if (startDate != null) { interviewEntityList = interviewEntityList.stream().filter(interviewEntity -> interviewEntity.getStartDate().equals(startDate)).collect(Collectors.toList()); }
        if (endDate != null) { interviewEntityList = interviewEntityList.stream().filter(interviewEntity -> interviewEntity.getEndDate().equals(endDate)).collect(Collectors.toList()); }
        if (interviewName != null) { interviewEntityList = interviewEntityList.stream().filter(interviewEntity -> interviewEntity.getInterviewName().equals(interviewName)).collect(Collectors.toList()); }
        if (active != null && (active.equals("true") || active.equals("false"))) { interviewEntityList = interviewEntityList.stream().filter(interviewEntity -> interviewEntity.isActive() == (Boolean.parseBoolean(active))).collect(Collectors.toList()); }

        return interviewEntityList;
    }

    /**
     * Получить опрос по id
     */
    public InterviewEntity getInterview(UUID id) throws ResourceNotFoundException {
        return interviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    /**
     * Изменить опрос по id
     */
    public void editInterview(UUID id, InterviewEditRequest editRequest) throws ResourceNotFoundException {
        InterviewEntity interviewEntity = interviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());

        if (editRequest.getInterviewName() != null) { interviewEntity.setInterviewName(editRequest.getInterviewName()); }
        if (editRequest.getStartDate() != null) { interviewEntity.setStartDate(editRequest.getStartDate()); }
        if (editRequest.getEndDate() != null) { interviewEntity.setEndDate(editRequest.getEndDate()); }
        if (editRequest.getActive().equals("true") || editRequest.getActive().equals("false")) { interviewEntity.setActive(Boolean.parseBoolean(editRequest.getActive())); }

        interviewRepository.save(interviewEntity);
    }

    /**
     * Удалить опрос по id
     */
    public void deleteInterview(UUID id) {
        interviewRepository.deleteById(id);
    }
}
