package Coaches.models;

import Coaches.persistence.entity.Coach;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/***
 * Объект содержащий полную информацию о тренере
 */
@Data
@Builder
@Schema(description = "Dto тренера")
public class CoachDto {

    @Schema(description = "Id", example = "'UUID'")
    public UUID id;

    @Schema(description = "Фамилия", example = "Петренко")
    public String lastname;

    @Schema(description = "Имя", example = "Петр")
    public String firstname;

    @Schema(description = "Отчество", example = "Петрович")
    public String surname;

    @Schema(description = "Возраст", example = "25")
    public int age;

    @Schema(description = "Статус архивации", example = "false")
    public boolean archivedStatus;

    @Schema(description = "Email", example = "coach@gmail.com")
    public String email;

    @Schema(description = "Дата рождения")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate birthday;

    @Schema(description = "Номер телефона", example = "89883717322")
    public String phoneNumber;

    public static CoachDto toCoachDto(Coach coach) {
        return CoachDto.builder()
                .id(coach.getId())
                .lastname(coach.getLastname())
                .firstname(coach.getFirstname())
                .surname(coach.getSurname())
                .age(coach.getAge())
                .birthday(coach.getBirthday())
                .phoneNumber(coach.getPhoneNumber())
                .email(coach.getEmail())
                .archivedStatus(coach.isArchivedStatus())
                .build();
    }
}
