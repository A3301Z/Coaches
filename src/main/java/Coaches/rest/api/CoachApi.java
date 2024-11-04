package Coaches.rest.api;

import Coaches.models.Coach.CoachDto;
import Coaches.models.Coach.CoachMinimalDto;
import Coaches.models.Coach.CreateCoachDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Tag(name = "API тренеров")
@RequestMapping("/api/v3/coaches")
public interface CoachApi {

    @Operation(summary = "Добавить")
    @PostMapping(value = "/coach")
    void addCoach(@RequestBody CreateCoachDto createCoachDto);

    @Operation(summary = "Обновить")
    @PostMapping(value = "/coach/update")
    void update(@RequestBody CoachDto coachDto);

    @Operation(summary = "Получить полную информацию")
    @GetMapping(value = "/coach/{id}")
    CoachDto getFullInfo(@PathVariable UUID id);

    @Operation(summary = "Получить список всех тренеров")
    @GetMapping(value = "/all")
    List<CoachMinimalDto> getAll();

    @Operation(summary = "Обновить статус архивации")
    @PutMapping(value = "/archiving/{id}")
    ResponseEntity<?> updateArchivingStatus(@PathVariable UUID id);

    @Operation(summary = "Удаление профиля")
    @DeleteMapping(value = "delete/{id}")
    void deleteById(@PathVariable UUID id);
}
