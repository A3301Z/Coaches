package Coaches.Services;

import Coaches.Entity.Coach;
import Coaches.Exceptions.CoachNotFoundException;
import Coaches.Models.CoachDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class CoachesServices {
    Coach one = new Coach(UUID.randomUUID(), "Regina", "Manuylova", 26);
    Coach two = new Coach(UUID.randomUUID(), "Nikolay", "Korshunov", 29);
    Coach three = new Coach(UUID.randomUUID(), "Kirill", "Sarychev", 40);

    private List<Coach> coaches = new ArrayList<>(Arrays.asList(one, two, three));

    public CoachesServices() {
    }

    public List<Coach> getAllCoaches() {
        return coaches;
    }

    public Coach getFullInfo() {
        LocalDate birthday = LocalDate.of(1998, Month.SEPTEMBER, 29);
        String phoneNumber = "+7(918) 888 77-99";
        String email = "coach@mail.com";
        return new Coach(UUID.randomUUID(), three.getFirstname(), three.getSecondname(), three.getAge(), birthday, phoneNumber, email, three.getArchivedStatus());
    }

    public Optional<Coach> getById(UUID id) {
        return getAllCoaches().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public void updateArchivedStatus(UUID id) {
        getById(id).ifPresent(coach -> coach.setArchived());
    }

    public void add(Coach coach) {
        coaches.add(coach);
    }

    public void updateCoach(Coach source) throws CoachNotFoundException {
        Optional<Coach> coachOptional = getById(source.getId());

        if (coachOptional.isPresent()) {
            Coach target = coachOptional.get();
            target.setFirstname(source.getFirstname());
            target.setSecondname(source.getSecondname());
            target.setAge(source.getAge());
            target.setBirthday(source.getBirthday());
            target.setPhoneNumber(source.getPhoneNumber());
            target.setEmail(source.getEmail());
        } else {
            throw new CoachNotFoundException(String.format("Тренер с идентификатором %s не найден", source.getId()));
        }
    }
}