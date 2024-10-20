package Coaches.persistence.models;

import Coaches.Entity.Coach;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.UUID;

/***
 * Объект содержащий минимум информации о тренере
 */
@Builder
public class CoachMinimalDto {

    /**
     * Уникальный идентификатор тренера
     */
    @Schema(description = "Идентификатор")
    public UUID id;

    /**
     * Фамилия
     */
    @Schema(description = "Фамилия")
    public String lastname;

    /**
     * Имя
     */
    @Schema(description = "Имя")
    public String firstname;

    /**
     * Отчество
     */
    @Schema(description = "Отчество")
    public String surname;

    /**
     * Возраст
     */
    @Schema(description = "Возраст")
    public int age;

    /**
     * Заархивирован ли профиль тренера? true/false
     */
    @Schema(description = "Заархивирован ли профиль тренера? true/false")
    public boolean isArchived;

    /**
     * Время архивации профиля тренера
     */
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Schema(description = "Время архивации профиля")
    public Timestamp archivingTime;

    public static CoachMinimalDto toCoachMinimalDto(Coach coach) {
        return CoachMinimalDto.builder()
                .lastname(coach.getLastname())
                .firstname(coach.getFirstname())
                .surname(coach.getSurname())
                .age(coach.getAge())
                .isArchived(coach.isArchived())
                .archivingTime(coach.getArchivingTime())
                .build();
    }
}
