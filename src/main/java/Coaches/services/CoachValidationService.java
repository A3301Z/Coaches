package Coaches.services;

import Coaches.persistence.models.CreateCoachDto;

/**
 * Сервис валидации тренеров
 */
public interface CoachValidationService {

    /**
     * Проверка на дубликат
     */
    boolean isDuplicate(CreateCoachDto createCoachDto);
}
