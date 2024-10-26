package Coaches.services.impl;

import Coaches.persistence.models.CreateCoachDto;
import Coaches.services.CoachValidationService;
import org.springframework.stereotype.Service;

@Service
public class CoachValidationServiceImpl implements CoachValidationService {

    public boolean isDuplicate(CreateCoachDto createCoachDto) {
        return false;
    }
}
