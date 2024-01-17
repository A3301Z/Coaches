package Coaches.Controllers;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import Coaches.Models.CoachMinimalDto;
import Coaches.Services.CoachesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CoachController {
    @Autowired
    private CoachesServices services;

    @GetMapping("/all-coaches")
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

    @GetMapping("/coach/{id}")
    public ResponseEntity<Coach> getById(@PathVariable UUID id) {
        Optional<Coach> coach = services.getById(id);
        return coach
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/coach/add")
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

    @DeleteMapping("/coach/archived-status/{id}")
    public void archiveCoach(@PathVariable UUID id) {

        // TODO: следует обработать ситуацию если передан идентификатор тренера, которого нет

        services.updateArchivedStatus(id);
    }

    @PutMapping("/coach/update")
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
