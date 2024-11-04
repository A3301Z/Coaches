package Coaches.services.impl;

import Coaches.models.Coach.CreateCoachDto;
import Coaches.persistence.repository.CoachRepository;
import Coaches.services.CoachValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoachValidationServiceImpl implements CoachValidationService {

    private final CoachRepository coachRepository;

    public boolean isDuplicate(CreateCoachDto createCoachDto) {
        log.debug("#isDuplicate: createCoachDto = {}", createCoachDto);

        return coachRepository.findCoachByFields(
                        createCoachDto.lastname,
                        createCoachDto.firstname,
                        createCoachDto.surname,
                        createCoachDto.email,
                        createCoachDto.phoneNumber,
                        createCoachDto.age
                )
                .map(CreateCoachDto::toCreateCoachDto)
                .map(createCoachDto::equals)
                .orElse(false);
    }
}
