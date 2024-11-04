package Coaches.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Tag(name = "API контент")
@RequestMapping("/api/v3/coaches")
public interface ContentApi {
    @Operation(summary = "Сохранить главное фото")
    @PostMapping(value = "/coach/saveContent", consumes = "multipart/form-data")
    void saveContent(
            @RequestParam UUID coachId,
            @RequestParam MultipartFile content,
            @RequestParam boolean isMain
    );

    @Operation(summary = "Получить главное фото тренера")
    @GetMapping(value = "/coach/mainPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getMainPhoto(@RequestParam UUID coachId);
}
