package Coaches.Services;

import Coaches.Entity.Coach;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class CoachesServices {
    Coach one = new Coach(UUID.randomUUID(), "Aaa", "Aaa", 111);;
    Coach two = new Coach(UUID.randomUUID(), "Bbb", "Bbb", 222);;
    Coach three = new Coach(UUID.randomUUID(), "Ccc", "Ccc", 333);

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
        Coach coachFullInfo = new Coach(UUID.randomUUID(), three.getFirstName(), three.getSecondName(), three.getAge(), birthday, phoneNumber, email);
        return coachFullInfo;
    }

    public Optional<Coach> getById(UUID id) {
        return getAllCoaches().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public void add(Coach coach) {
        coaches.add(coach);
    }
}
