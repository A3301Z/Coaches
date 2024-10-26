package Coaches.rest.api;

import Coaches.models.CoachDto;
import Coaches.models.CoachMinimalDto;
import Coaches.models.CreateCoachDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "API тренеров")
@RequestMapping("/api/v3/coaches")
public interface CoachApi {

    @Operation(summary = "Добавить")
    @PostMapping(value = "/coach")
    void addCoach(@RequestBody CreateCoachDto createCoachDto);

    @Operation(summary = "Получить полную информацию")
    @GetMapping(value = "/coach/{id}")
    CoachDto getFullInfo(@PathVariable UUID id);

    @Operation(summary = "Получить список всех тренеров")
    @GetMapping(value = "/all")
    List<CoachMinimalDto> getAll();

    @Operation(summary = "Отправить в архив")
    @PutMapping(value = "/archiving/{id}")
    ResponseEntity<?> updateArchivingStatus(@PathVariable UUID id);
}
