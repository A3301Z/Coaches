package Coaches.models.Content;

import Coaches.persistence.entity.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Builder
@EqualsAndHashCode
@Schema(description = "Dto для сохранения фотографий тренера")
public class ContentDto {

    @Schema(description = "Идентификатор фотографии", example = "'UUID'")
    public UUID id;

    @Schema(description = "Идентификатор тренера", example = "'UUID'")
    public UUID coach_id;

    @Schema(description = "Имя файла", example = "Photo.jpeg")
    public String fileName;

    @Schema(description = "Фото тренера в формате массива байт")
    public byte[] photo;

    @Schema(description = "Флаг главного фото", example = "true/false")
    public boolean isMain;

    public static ContentDto toContentDto(Content content) {
        return ContentDto.builder()
                .id(content.getId())
                .coach_id(content.getCoachId())
                .fileName(content.getFileName())
                .photo(content.getPhoto())
                .isMain(content.isMain())
                .build();
    }
}
