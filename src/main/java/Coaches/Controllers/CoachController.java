package Coaches.Controllers;

import Coaches.Entity.Coach;
import Coaches.Models.CoachDto;
import Coaches.Models.CoachMinimalDto;
import Coaches.Services.CoachesServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "Описание работы REST функционала.")
public class CoachController {
    @Autowired
    private CoachesServices services;

    @GetMapping("/all-coaches")
    @ApiOperation("Получение краткой информации о всех тренерах.")
    public List<CoachMinimalDto> getAllCoaches() {
        List<CoachMinimalDto> result = new ArrayList<>();

        for (Coach it : services.getAllCoaches()) {
            CoachMinimalDto dto = new CoachMinimalDto();
            dto.Id = it.getId();
            dto.FirstName = it.getFirstName();
            dto.SecondName = it.getSecondName();

            result.add(dto);
        }

        return result;
    }

    @GetMapping("/coach/{id}")
    @ApiOperation("Получение полной информации о конкретном тренере.")
    public ResponseEntity<Coach> getById(@PathVariable UUID id) {
        var coach = services.getById(id);
        if (coach.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coach.get(), HttpStatus.OK);
    }

    @PostMapping("/coach")
    @ApiOperation("Добавление нового тренера.")
    public void createCoach(@RequestBody CoachDto dto) {
        var coach = new Coach(dto.Id, dto.FirstName, dto.SecondName, 11);
        services.add(coach);
    }

    @PutMapping("/coach")
    @ApiOperation("Редактирование тренера.")
    public Coach updateCoach() {
        return services.getFullInfo();
    }

    @DeleteMapping("/coach/{id}")
    @ApiOperation("Архивация информации о тренере.")
    public void archiveCoach(@PathVariable UUID id) {

        //return services.getThirdCoach();
    }
}
