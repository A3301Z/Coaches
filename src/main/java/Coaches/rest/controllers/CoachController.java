package Coaches.rest.controllers;

import Coaches.models.CoachDto;
import Coaches.models.CoachMinimalDto;
import Coaches.models.CreateCoachDto;
import Coaches.rest.api.CoachApi;
import Coaches.services.impl.CoachServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CoachController implements CoachApi {

    private final CoachServiceImpl coachService;

    @Override
    public void addCoach(CreateCoachDto createCoachDto) {
        coachService.addCoach(createCoachDto);
    }

    @Override
    public CoachDto getFullInfo(UUID id) {
        return coachService.getFullInfo(id);
    }

    @Override
    public List<CoachMinimalDto> getAll() {
        return coachService.getAll();
    }

    @Override
    public ResponseEntity<?> updateArchivingStatus(UUID id) {
        return coachService.updateArchivingStatus(id);
    }
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
