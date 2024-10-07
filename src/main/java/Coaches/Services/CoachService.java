package Coaches.Services;

import Coaches.Entity.Coach;
import Coaches.Exceptions.NotFoundException;
import Coaches.persistence.Models.CoachDto;
import Coaches.Repository.CoachRepository;
import Coaches.persistence.Models.CoachMinimalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository repository;

    public void addCoach(CoachDto coachDto) {
        repository.save(Coach.toCoach(coachDto));
    }

    public CoachDto getFullInfo(UUID id) {
        return repository.findById(id).stream()
                .map(CoachDto::toCoachDto)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Тренер с %s не найден", id)));
    }

    public List<CoachMinimalDto> getAll() {
        return repository.findAll().stream()
                .map(CoachMinimalDto::toCoachMinimalDto)
                .collect(Collectors.toList());
    }
//    orElseThrow();
//    public List<Coach> getAllCoaches() {
//        return repository.getAllCoaches();
//    }
//
//    public Optional<Coach> getById(UUID id) {
//        return repository.getById(id);
//    }
//
//    public void updateArchivedStatus(UUID id) throws CoachNotFoundException {
//        repository.updateArchivedStatus(id);
//    }
//
//    public void updateCoach(CoachDto coachDto) {
//        repository.updateCoach(coachDto);
//    }
//
//    public void deleteById(UUID id) {
//        repository.deletedByID(id);
//    }
}
