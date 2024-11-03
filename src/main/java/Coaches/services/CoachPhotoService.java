package Coaches.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Сервис сохранение фото тренера
 */
public interface CoachPhotoService {

    /**
     * Метод для сохранения фотографий тренера
     * @param coachId - идентификатор тренера
     * @param isMain - флаг, является ли фото главным
     * @param file - передаваемый файл
     */
    void saveContent(UUID coachId, MultipartFile file, boolean isMain);
}
