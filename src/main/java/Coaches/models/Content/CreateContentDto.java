package Coaches.models.Content;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Builder
@EqualsAndHashCode
@Schema(description = "Dto для сохранения фотографий тренера")
public class CreateContentDto {

    @Schema(description = "Идентификатор тренера", example = "'UUID'")
    public UUID coach_id;

    @Schema(description = "Имя файла", example = "Photo.jpeg")
    public String fileName;

    @Schema(description = "Фото тренера в формате массива байт")
    public byte[] photo;

    @Schema(description = "Флаг главного фото", example = "true/false")
    public boolean isMain;
}
