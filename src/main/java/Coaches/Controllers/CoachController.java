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

import java.sql.SQLException;
import java.util.*;

@RestController
public class CoachController {
	private final CoachesServices services;

	public CoachController(@Autowired
	CoachesServices services) {
		this.services = services;
	}

	@Tag(name = "Получить неполную информацию о всех тренерах.")
	@GetMapping("/coaches")
	public List<CoachMinimalDto> getAll() {
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

	@Tag(name = "Получить детальную информацию о тренере.")
	@GetMapping("/coach/{id}")
	public ResponseEntity<Coach> getById(@PathVariable
	UUID id) {
		Optional<Coach> coach = services.getById(id);
		return coach.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Tag(name = "Добавить нового тренера.")
	@PostMapping("/coach")
	public void createCoach(@RequestBody
	CoachDto dto) {
		Coach coach = new Coach(dto.Id, dto.Firstname, dto.Secondname, dto.Age, dto.Birthday, dto.Phonenumber,
				dto.Email, dto.Archived);
		services.add(coach);
	}

	@Tag(name = "Отправить тренера в архив.")
	@DeleteMapping("/coach/{id}")
	public ResponseEntity<?> archiveCoach(@PathVariable
	UUID id) {

		try {
			services.updateArchivedStatus(id);
		}
		catch (CoachNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Tag(name = "Обновить поля существующего тренера.")
	@PutMapping("/coach")
	public ResponseEntity<?> updateCoach(@RequestBody
	CoachDto dto) {
		services.updateCoach(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Tag(name = "Удаление тренера из базы данных")
	@DeleteMapping("/coach/delete/{id}")
	public void deleteById(@PathVariable
	UUID id) {
		services.deleteById(id);
	}
}
