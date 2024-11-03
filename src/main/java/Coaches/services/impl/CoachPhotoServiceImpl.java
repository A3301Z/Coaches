package Coaches.services.impl;

import Coaches.persistence.repository.CoachPhotoRepository;
import Coaches.services.CoachPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoachPhotoServiceImpl implements CoachPhotoService {

    private final CoachPhotoRepository photoRepository;

    @Transactional
    public void saveContent(UUID coachId, MultipartFile file, boolean isMain) {

        try {
            if (isMain) photoRepository.doNotMain();

            String fileName = file.getOriginalFilename();
            byte[] content = file.getBytes();

            this.photoRepository.saveContent(coachId, fileName, content, isMain);

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

    public byte[] getMainPhoto(UUID coachId) {
        return this.photoRepository.getMainPhoto(coachId);
    }
}