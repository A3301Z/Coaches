package Coaches.Controllers;

import Coaches.Entity.Coach;
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
        if (coach.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coach.get(), HttpStatus.OK);
    }

    @PostMapping("/coach/add")
    public void createCoach(@RequestBody CoachDto dto) {
        Coach coach = new Coach(dto.Id, dto.Firstname, dto.Secondname, dto.Age);
        services.add(coach);
    }

    @DeleteMapping("/coach/archived-status/{id}")
    public void archiveCoach(@PathVariable UUID id, @RequestParam boolean archived) {
        services.updateArchivedStatus(id, archived);
    }

    @PutMapping("/coach/update/{id}")
    public void updateCoach(@PathVariable UUID id, @RequestBody CoachDto dto) {
        Optional<Coach> coachOptional = services.getById(id);

        if (coachOptional.isPresent()) {
            Coach coach = coachOptional.get();

            // Обновляем все поля существующего тренера
            coach.setFirstname(dto.Firstname);
            coach.setSecondname(dto.Secondname);
            coach.setAge(dto.Age);
            coach.setBirthday(dto.Birthday);
            coach.setPhoneNumber(dto.PhoneNumber);
            coach.setEmail(dto.Email);
            coach.setArchived(dto.Archived);
        }
    }
}
