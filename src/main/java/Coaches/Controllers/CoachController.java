package Coaches.Controllers;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import Coaches.Models.CoachMinimalDto;
import Coaches.Services.CoachesServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CoachController {
    @Autowired
    private CoachesServices services;

    @Tag(name="Получить неполную информацию о всех тренерах.")
    @GetMapping("/coaches")
    public List<CoachMinimalDto> getAllCoaches() {
        List<CoachMinimalDto> result = new ArrayList<>();

        for (Coach it : services.getAllCoaches()) {
            CoachMinimalDto dto = new CoachMinimalDto();
            dto.Id = it.getId();
            dto.Firstname = it.getFirstname();
            dto.Secondname = it.getSecondname();
            dto.Age = it.getAge();
            dto.Archived = it.getArchivedStatus();

            result.add(dto);
        }

        return result;
    }
    @Tag(name="Получить детальную информацию о тренере.")
    @GetMapping("/coach/{id}")
    public ResponseEntity<Coach> getById(@PathVariable UUID id) {
        Optional<Coach> coach = services.getById(id);
        return coach
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Tag(name="Добавить нового тренера.")
    @PostMapping("/coach")
    public void createCoach(@RequestBody CoachDto dto) {
        Coach coach = new Coach(dto.Id,
                dto.Firstname,
                dto.Secondname,
                dto.Age,
                dto.Birthday,
                dto.PhoneNumber,
                dto.Email,
                dto.Archived);

        services.add(coach);
    }

    @Tag(name="Обновить поля существующего тренера.")
    @PutMapping("/coach")
    public ResponseEntity<?> updateCoach(@RequestBody CoachDto dto) {
        Coach coach = new Coach(dto.Id,
                dto.Firstname,
                dto.Secondname,
                dto.Age,
                dto.Birthday,
                dto.PhoneNumber,
                dto.Email,
                dto.Archived);

        try
        {
            services.updateCoach(coach);
        } catch (CoachNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
