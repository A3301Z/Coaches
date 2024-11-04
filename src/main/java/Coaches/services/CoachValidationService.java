package Coaches.services;

import Coaches.models.Coach.CreateCoachDto;

/**
 * Сервис валидации тренеров
 */
public interface CoachValidationService {

    /**
     * Проверка на дубликат
     */
    boolean isDuplicate(CreateCoachDto createCoachDto);
}
