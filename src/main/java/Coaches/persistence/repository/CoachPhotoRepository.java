package Coaches.persistence.repository;

import Coaches.persistence.entity.Coach;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Repository
public interface CoachPhotoRepository extends CrudRepository<Coach, UUID> {

    /**
     * Метод для сохранения фотографий тренера
     * @param coachId - идентификатор тренера
     * @param fileName - имя файла
     * @param content - фото в формате набора байт
     * @param isMain - флаг, является ли фото главным
     */
    @Modifying
    @Query("""
           insert into content (coach_id, photo, is_main, file_name)
           values (:coachId, :content, :isMain, :fileName)
           """)
    void saveContent(UUID coachId, String fileName, byte[] content, boolean isMain);

    /**
     * Сделать предыдущее фото не главным, если сохранено новое фото с флагом isMain = true
     */
    @Modifying
    @Query("""
           update content
           set is_main = false
           where is_main = true
           """)
    void doNotMain();

    /**
     * Получить главное фото тренера
     */
    @Query("select photo from content where coach_id = :coachId")
    byte[] getMainPhoto(UUID coachId);
}
