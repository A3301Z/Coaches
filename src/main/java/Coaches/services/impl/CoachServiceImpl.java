package Coaches.services.impl;

import Coaches.persistence.entity.Coach;
import Coaches.persistence.models.CoachDto;
import Coaches.persistence.models.CreateCoachDto;
import Coaches.repository.CoachRepository;
import Coaches.persistence.models.CoachMinimalDto;
import Coaches.services.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final CoachValidationServiceImpl coachValidationService;

    public void addCoach(CreateCoachDto createCoachDto) {

        coachRepository.save(Coach.toCoach(createCoachDto));
    }

    public CoachDto getFullInfo(UUID id) {
        return coachRepository.findById(id).stream()
                .map(CoachDto::toCoachDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Тренер с %s не найден", id)));
    }

    public List<CoachMinimalDto> getAll() {
        return coachRepository.findAll().stream()
                .map(CoachMinimalDto::toCoachMinimalDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> updateArchivingStatus(UUID id) {
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
