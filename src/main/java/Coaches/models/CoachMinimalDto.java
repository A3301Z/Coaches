package Coaches.models;

import Coaches.persistence.entity.Coach;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

/***
 * Объект содержащий минимум информации о тренере
 */
@Builder
@Schema(description = "Dto тренера минимальные данные", example = "'UUID'")
public class CoachMinimalDto {

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
    public boolean isArchived;

    public static CoachMinimalDto toCoachMinimalDto(Coach coach) {
        return CoachMinimalDto.builder()
                .lastname(coach.getLastname())
                .firstname(coach.getFirstname())
                .surname(coach.getSurname())
                .age(coach.getAge())
                .isArchived(coach.isArchived())
                .build();
    }
}
