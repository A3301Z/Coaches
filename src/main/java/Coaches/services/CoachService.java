package Coaches.services;

import Coaches.persistence.models.CoachDto;
import Coaches.persistence.models.CoachMinimalDto;
import Coaches.persistence.models.CreateCoachDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CoachService {

    /**
     * Сохранить нового тренера в базу
     * @param createCoachDto - dto для сохранения нового тренера
     */
    void addCoach(CreateCoachDto createCoachDto);

    /**
     * Получить полную информацию о тренере
     * @param id - UUID
     * @return - Dto содержащее полную информацию о тренере
     */
    CoachDto getFullInfo(UUID id);

    /**
     * Получить список всех тренеров
     * @return - список дто всех тренеров содержащий минимум информации
     */
    List<CoachMinimalDto> getAll();

    /**
     * Изменить статус архивации профиля
     * @param id - UUID
     * @return - статус операции
     */
    ResponseEntity<?> updateArchivingStatus(UUID id);
}
