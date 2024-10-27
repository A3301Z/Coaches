package Coaches.services.impl;

import Coaches.exceptions.CoachDuplicateException;
import Coaches.exceptions.NotFoundException;
import Coaches.models.CoachDto;
import Coaches.models.CoachMinimalDto;
import Coaches.models.CreateCoachDto;
import Coaches.persistence.entity.Coach;
import Coaches.persistence.repository.CoachRepository;
import Coaches.services.CoachService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final CoachValidationServiceImpl coachValidationService;

    public void addCoach(CreateCoachDto createCoachDto) {
        log.debug("#addCoach: createCoachDto = {}", createCoachDto);

        Optional.of(createCoachDto)
                .filter(dto -> !coachValidationService.isDuplicate(dto))
                .map(Coach::toCoach)
                .ifPresentOrElse(coachRepository::save, () -> {
                    throw new CoachDuplicateException("Конфликт: попытка сохранить дубликат");
                });
    }

    public CoachDto getFullInfo(UUID id) {
        log.debug("#getFullInfo: id = {}", id);
        return coachRepository.findById(id).stream()
                .map(CoachDto::toCoachDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Тренер с %s не найден", id)));
    }

    public List<CoachMinimalDto> getAll() {
        log.debug("#getAll");
        return coachRepository.findAll().stream()
                .map(CoachMinimalDto::toCoachMinimalDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> updateArchivingStatus(UUID id) {
        log.debug("#updateArchivingStatus: id = {}", id);

        return toggleArchivedStatus(id) ?
                ResponseEntity.ok("Статус успешно обновлен") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Тренер с указанным ID не найден");

    }

    public void updateCoach(CoachDto coachDto) {
        log.debug("#updateCoach: coachDto = {}", coachDto);

        coachRepository.findById(coachDto.id).orElseThrow(
                () -> new NotFoundException(
                        String.format("Не удалось обновить профиль, тренер с id = %s не найден", coachDto.id))
        );

        Optional.of(coachDto)
                .filter(dto -> !coachValidationService.isDuplicate(CreateCoachDto.toCreateCoachDto(dto)))
                .map(Coach::toCoach)
                .ifPresentOrElse(coachRepository::save, () -> {
                    throw new CoachDuplicateException("Конфликт: попытка сохранить дубликат");
                });

        coachRepository.save(Coach.toCoachForUpdating(coachDto));
    }

    public void deleteById(UUID id) {
        log.debug("#deleteById: id = {}", id);
        coachRepository.deleteById(id);
    }

    public boolean toggleArchivedStatus(UUID id) {
        return coachRepository.findById(id).map(coach -> {
            coach.setArchivedStatus(!coach.isArchivedStatus());
            coachRepository.save(coach);
            return true;
        }).orElse(false);
    }
}
