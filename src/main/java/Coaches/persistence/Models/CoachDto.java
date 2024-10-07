package Coaches.persistence.Models;

import Coaches.Entity.Coach;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

/***
 * Объект содержащий полную информацию о тренере
 */
@Builder
public class CoachDto {

    /**
     * Уникальный идентификатор тренера
     */
    public UUID id;

    /**
     * Фамилия
     */
    public String lastname;

    /**
     * Имя
     */
    public String firstname;

    /**
     * Отчество
     */
    public String surname;

    /**
     * Возраст
     */
    public int age;

    /**
     * Заархивирован ли профиль тренера? true/false
     */
    public boolean isArchived;

    /**
     * Время архивации профиля тренера
     */
    public Timestamp archivingTime;

    /**
     * Тренер в архиве
     */
    public String email;

    /**
     * Тренер в архиве
     */
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate birthday;

    /**
     * Тренер в архиве
     */
    public String phoneNumber;

    public static CoachDto toCoachDto(Coach coach) {
        return CoachDto.builder()
                .lastname(coach.getLastname())
                .firstname(coach.getFirstname())
                .surname(coach.getSurname())
                .age(coach.getAge())
                .birthday(coach.getBirthday())
                .phoneNumber(coach.getPhoneNumber())
                .email(coach.getEmail())
                .isArchived(coach.isArchived())
                .archivingTime(coach.getArchivingTime())
                .build();
    }
}
