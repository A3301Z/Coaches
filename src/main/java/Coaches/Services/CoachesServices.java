package Coaches.Services;

import Coaches.Entity.Coach;
import org.springframework.stereotype.Service;

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

    public void updateArchivedStatus(UUID id, boolean archived) {
        getById(id).ifPresent(coach -> coach.setArchived(archived));
    }
    public void add(Coach coach) {
        coaches.add(coach);
    }
}
