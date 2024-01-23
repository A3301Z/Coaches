package Coaches.Services;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import Coaches.Repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Service
public class CoachesServices {

    private final CoachRepository repository = new CoachRepository();

    public CoachesServices() {
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
}
