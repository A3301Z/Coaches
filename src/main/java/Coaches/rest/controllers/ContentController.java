package Coaches.rest.controllers;

import Coaches.rest.api.ContentApi;
import Coaches.services.CoachPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ContentController implements ContentApi {

    private final CoachPhotoService coachPhotoService;

    @Override
    public void saveContent(UUID coachId, MultipartFile file, boolean is_main) {
        this.coachPhotoService.saveContent(coachId, file, is_main);
    }

    @Override
    public byte[] getMainPhoto(UUID coachId) {
        return this.coachPhotoService.getMainPhoto(coachId);
    }
}
