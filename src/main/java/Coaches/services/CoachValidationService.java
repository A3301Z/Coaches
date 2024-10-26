package Coaches.services;

import Coaches.models.CreateCoachDto;

/**
 * Сервис валидации тренеров
 */
public interface CoachValidationService {

    /**
     * Проверка на дубликат
     */
    boolean isDuplicate(CreateCoachDto createCoachDto);
}
