package Coaches.services.impl;

import Coaches.persistence.repository.CoachPhotoRepository;
import Coaches.services.CoachPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoachPhotoServiceImpl implements CoachPhotoService {

    private final CoachPhotoRepository photoRepository;

//    public void save(UUID coachId, String fileName, byte[] content, boolean isMain) {
//        this.photoRepository.save(coachId, fileName, content, isMain);
//    }
//
//    public byte[] getMainPhoto(UUID id) {
//        return this.photoRepository.getMainPhoto(id);
//    }
}