package Coaches.Services;

import Coaches.Repository.CoachPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoachPhotoService {

    private final CoachPhotoRepository photoRepository;

    public CoachPhotoService(@Autowired CoachPhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void add(UUID coachId, String fileName, byte[] content) throws Exception {
        var alreadyUploaded = this.photoRepository.isFileAlreadyUploaded(coachId, fileName);

        if (!alreadyUploaded) {
            this.photoRepository.add(coachId, fileName, content);
        } else {
            throw new Exception("Файл с таким именем уже был загружен");
        }
    }

    public byte[] getMainPhoto(UUID id) {
        return this.photoRepository.getMainPhoto(id);
    }
}
