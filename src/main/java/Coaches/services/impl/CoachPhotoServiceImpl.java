package Coaches.services.impl;

import Coaches.persistence.repository.CoachPhotoRepository;
import Coaches.services.CoachPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoachPhotoServiceImpl implements CoachPhotoService {

    private final CoachPhotoRepository photoRepository;

    @Transactional
    public void saveContent(UUID coachId, MultipartFile file, boolean isMain) {
        log.debug("#saveContent: coachId = {}, fileName = {}, isMain= {}", coachId, file.getOriginalFilename(), isMain);
        try {
            if (isMain) photoRepository.doNotMain();

            String fileName = file.getOriginalFilename();
            byte[] content = file.getBytes();
            LocalDateTime fileSavingDateTime = LocalDateTime.now();

            this.photoRepository.saveContent(coachId, fileName, content, isMain, fileSavingDateTime);

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new RuntimeException();
        }
    }

    public byte[] getMainPhoto(UUID coachId) {
        log.debug("#getMainPhoto: coachId = {}", coachId);
        return this.photoRepository.getMainPhoto(coachId).getPhoto();
    }
}