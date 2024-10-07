package Coaches.Rest.Api;

import Coaches.persistence.Models.CoachDto;
import Coaches.persistence.Models.CoachMinimalDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "API тренеров")
@RequestMapping("/api/v3/coaches")
public interface CoachApi {

    @Operation(summary = "Добавить")
    @PostMapping(value = "/coach")
    void addCoach(@RequestBody CoachDto coachDto);

    @Operation(summary = "Получить полную информацию о тренере")
    @GetMapping(value = "/coach/{id}")
    CoachDto getFullInfo(UUID id);

    @Operation(summary = "Получить список всех тренеров")
    @GetMapping(value = "/all")
    List<CoachMinimalDto> getAll();
}
