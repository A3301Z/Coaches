package Coaches.Controllers;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import Coaches.Models.CoachMinimalDto;
import Coaches.Services.CoachPhotoService;
import Coaches.Services.CoachesServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@RestController
public class CoachController {
<<<<<<< HEAD
	private final CoachesServices services;

	public CoachController(@Autowired
	CoachesServices services) {
		this.services = services;
	}
=======
    private final CoachesServices coachesServices;
    private final CoachPhotoService coachPhotoService;

    public CoachController(@Autowired CoachesServices coachesServices, @Autowired CoachPhotoService coachPhotoService) {
        this.coachesServices = coachesServices;
        this.coachPhotoService = coachPhotoService;
    }
>>>>>>> debe8b8d77e56364885b10b637a0336656d8de1d

	@Tag(name = "Получить неполную информацию о всех тренерах.")
	@GetMapping("/coaches")
	public List<CoachMinimalDto> getAll() {
		List<CoachMinimalDto> result = new ArrayList<>();

<<<<<<< HEAD
		for (Coach it : services.getAllCoaches()) {
			CoachMinimalDto dto = new CoachMinimalDto();
			dto.Id = it.getId();
			dto.Firstname = it.getFirstname();
			dto.Secondname = it.getSecondname();
			dto.Age = it.getAge();
			dto.Archived = it.getArchivedStatus();
=======
        for (Coach it : coachesServices.getAllCoaches()) {
            CoachMinimalDto dto = new CoachMinimalDto();
            dto.Id         = it.getId();
            dto.Firstname  = it.getFirstname();
            dto.Secondname = it.getSecondname();
            dto.Age        = it.getAge();
            dto.Archived   = it.getArchivedStatus();
>>>>>>> debe8b8d77e56364885b10b637a0336656d8de1d

			result.add(dto);
		}

		return result;
	}

<<<<<<< HEAD
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
=======
    @Tag(name = "Получить детальную информацию о тренере.")
    @GetMapping("/coach/{id}")
    public ResponseEntity<Coach> getById(@PathVariable UUID id) {
        Optional<Coach> coach = coachesServices.getById(id);
        return coach.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Tag(name = "Добавить нового тренера.")
    @PostMapping("/coach")
    public void createCoach(@RequestBody CoachDto dto) {
        Coach coach = new Coach(dto.Id,
                                dto.Firstname,
                                dto.Secondname,
                                dto.Age,
                                dto.Birthday,
                                dto.Phonenumber,
                                dto.Email,
                                dto.Archived);
        coachesServices.add(coach);
    }
>>>>>>> debe8b8d77e56364885b10b637a0336656d8de1d

	@Tag(name = "Отправить тренера в архив.")
	@DeleteMapping("/coach/{id}")
	public ResponseEntity<?> archiveCoach(@PathVariable
	UUID id) {

<<<<<<< HEAD
		try {
			services.updateArchivedStatus(id);
		}
		catch (CoachNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
=======
        try {
            coachesServices.updateArchivedStatus(id);
        } catch (CoachNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
>>>>>>> debe8b8d77e56364885b10b637a0336656d8de1d

		return new ResponseEntity<>(HttpStatus.OK);
	}

<<<<<<< HEAD
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
=======
    @Tag(name = "Обновить поля существующего тренера.")
    @PutMapping("/coach")
    public ResponseEntity<?> updateCoach(@RequestBody CoachDto dto) {
        coachesServices.updateCoach(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "Удаление тренера из базы данных")
    @DeleteMapping("/coach/delete/{id}")
    public void deleteById(@PathVariable UUID id) {
        coachesServices.deleteById(id);
    }

    @Tag(name = "Загрузка фотографии тренера")
    @PostMapping(value = "/coach/{id}/uploadImage", consumes = "multipart/form-data")
    public void uploadImage(@RequestParam("file") MultipartFile file, @PathVariable UUID id) throws IOException {
        try {
            this.coachPhotoService.add(id, file.getName(), file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Tag(name = "Получение главной фотографии тренера")
    @GetMapping(value = "/coach/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto(@PathVariable UUID id) {
        return this.coachPhotoService.getMainPhoto(id);
    }
>>>>>>> debe8b8d77e56364885b10b637a0336656d8de1d
}
