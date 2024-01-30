package Coaches.Services;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import Coaches.Repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoachesServices {

    private final CoachRepository repository;

    public CoachesServices(@Autowired CoachRepository repository) {
        this.repository = repository;
    }

    public List<Coach> getAllCoaches() {
        return repository.getAllCoaches();
    }

    public Optional<Coach> getById(UUID id) {
        return repository.getById(id);
    }

    public void updateArchivedStatus(UUID id) throws CoachNotFoundException {
        repository.updateArchivedStatus(id);
    }

    public void add(Coach coach) {
        repository.add(coach);
    }

    public void updateCoach(CoachDto coachDto) {
        repository.updateCoach(coachDto);
    }

    public void deleteById(UUID id) {
        repository.deletedByID(id);
    }
}
