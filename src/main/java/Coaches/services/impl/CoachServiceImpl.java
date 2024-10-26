package Coaches.services.impl;

import Coaches.exceptions.CoachDuplicateException;
import Coaches.models.CoachDto;
import Coaches.models.CoachMinimalDto;
import Coaches.models.CreateCoachDto;
import Coaches.persistence.entity.Coach;
import Coaches.persistence.repository.CoachRepository;
import Coaches.services.CoachService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

        // Проверка на дубликат
        if (coachValidationService.isDuplicate(createCoachDto)) {
            throw new CoachDuplicateException("Возникл конфликт: попытка сохранить дубликат");
        }
        coachRepository.save(Coach.toCoach(createCoachDto));
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

    public ResponseEntity<?> updateArchivingStatus(UUID id) {
        log.debug("#updateArchivingStatus: id = {}", id);
        return coachRepository.updateArchivingStatus(id);
    }

//
//    public void updateCoach(CoachDto coachDto) {
//        repository.updateCoach(coachDto);
//    }
//
//    public void deleteById(UUID id) {
//        repository.deletedByID(id);
//    }
}
