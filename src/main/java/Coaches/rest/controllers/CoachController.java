package Coaches.rest.controllers;

import Coaches.models.Coach.CoachDto;
import Coaches.models.Coach.CoachMinimalDto;
import Coaches.models.Coach.CreateCoachDto;
import Coaches.rest.api.CoachApi;
import Coaches.services.impl.CoachPhotoServiceImpl;
import Coaches.services.impl.CoachServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CoachController implements CoachApi {

    private final CoachServiceImpl coachService;

    @Override
    public void addCoach(CreateCoachDto createCoachDto) {
        this.coachService.addCoach(createCoachDto);
    }

    @Override
    public CoachDto getFullInfo(UUID id) {
        return this.coachService.getFullInfo(id);
    }

    @Override
    public List<CoachMinimalDto> getAll() {
        return this.coachService.getAll();
    }

    @Override
    public ResponseEntity<?> updateArchivingStatus(UUID id) {
        return this.coachService.updateArchivingStatus(id);
    }

    @Override
    public void update(CoachDto coachDto) {
        this.coachService.updateCoach(coachDto);
    }

    @Override
    public void deleteById(UUID id) {
        this.coachService.deleteById(id);
    }
}
