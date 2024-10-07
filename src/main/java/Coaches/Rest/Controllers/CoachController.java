package Coaches.Rest.Controllers;

import Coaches.Rest.Api.CoachApi;
import Coaches.Services.CoachPhotoService;
import Coaches.Services.CoachService;
import Coaches.persistence.Models.CoachDto;
import Coaches.persistence.Models.CoachMinimalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CoachController implements CoachApi {

    private final CoachService service;

    private final CoachPhotoService coachPhotoService;

    public void addCoach(CoachDto coachDto) {
        service.addCoach(coachDto);
    }

    public CoachDto getFullInfo(UUID id) {
        return service.getFullInfo(id);
    }

    public List<CoachMinimalDto> getAll() {
        return service.getAll();
    }

//
//    @Tag(name = "Получить неполную информацию о всех тренерах.")
//    @GetMapping("/coaches")
//    public List<CoachMinimalDto> getAll() {
//        List<CoachMinimalDto> result = new ArrayList<>();
//
//        for (Coach it : coachesServices.getAllCoaches()) {
//            CoachMinimalDto dto = new CoachMinimalDto();
//            dto.Id         = it.getId();
//            dto.Firstname  = it.getFirstname();
//            dto.Secondname = it.getSecondname();
//            dto.Age        = it.getAge();
//            dto.Archived   = it.getArchivedStatus();
//            result.add(dto);
//        }
//
//        return result;
//    }
//
//    @Tag(name = "Получить детальную информацию о тренере.")
//    @GetMapping("/coach/{id}")
//    public ResponseEntity<Coach> getById(@PathVariable UUID id) {
//        Optional<Coach> coach = coachesServices.getById(id);
//        return coach.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @Tag(name = "Добавить нового тренера.")
//    @PostMapping("/coach")
//    public void createCoach(@RequestBody CoachDto dto) {
//        Coach coach = new Coach(dto.Id, dto.Firstname, dto.Secondname, dto.Age, dto.Birthday, dto.Phonenumber,
//                                dto.Email, dto.Archived);
//        coachesServices.add(coach);
//    }
//
//    @Tag(name = "Отправить тренера в архив.")
//    @DeleteMapping("/coach/{id}")
//    public ResponseEntity<?> archiveCoach(@PathVariable UUID id) {
//
//        try {
//            coachesServices.updateArchivedStatus(id);
//        } catch (CoachNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        try {
//            coachesServices.updateArchivedStatus(id);
//        } catch (CoachNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @Tag(name = "Обновить поля существующего тренера.")
//    @PutMapping("/coach")
//    public ResponseEntity<?> updateCoach(@RequestBody CoachDto dto) {
//        coachesServices.updateCoach(dto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @Tag(name = "Удаление тренера из базы данных")
//    @DeleteMapping("/coach/delete/{id}")
//    public void deleteById(@PathVariable UUID id) {
//        coachesServices.deleteById(id);
//    }
//
//    @Tag(name = "Загрузка фотографии тренера")
//    @PostMapping(value = "/coach/{id}/uploadImage", consumes = "multipart/form-data")
//    public void uploadImage(@RequestParam() MultipartFile file, @PathVariable UUID id, @RequestParam boolean is_main) {
//        try {
//            this.coachPhotoService.add(id, file.getOriginalFilename(), file.getBytes(), is_main);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Tag(name = "Получение главной фотографии тренера")
//    @GetMapping(value = "/coach/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] getPhoto(@PathVariable UUID id) {
//        return this.coachPhotoService.getMainPhoto(id);
//    }
}
