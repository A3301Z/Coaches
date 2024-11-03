package Coaches.rest.controllers;

import Coaches.models.CoachDto;
import Coaches.models.CoachMinimalDto;
import Coaches.models.CreateCoachDto;
import Coaches.rest.api.CoachApi;
import Coaches.services.impl.CoachPhotoServiceImpl;
import Coaches.services.impl.CoachServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CoachController implements CoachApi {

    private final CoachServiceImpl coachService;
    private final CoachPhotoServiceImpl coachPhotoService;

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

    @Override
    public void saveContent(MultipartFile file, UUID id, boolean is_main) {
        this.coachPhotoService.saveContent(id, file, is_main);
    }

    @Override
    public byte[] getMainPhoto(UUID coachId) {
        return this.coachPhotoService.getMainPhoto(coachId);
    }
}
