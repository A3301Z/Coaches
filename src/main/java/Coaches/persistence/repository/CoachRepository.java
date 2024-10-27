package Coaches.persistence.repository;

import Coaches.persistence.entity.Coach;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoachRepository extends CrudRepository<Coach, UUID> {

    @Query("select * from coaches")
    List<Coach> findAll();

    @Query("""
            select id, lastname, firstname, surname, age, birthday, phone_number, email, is_archived
            from coaches
            where
                lastname = :lastname and
                firstname = :firstname and
                surname = :surname and
                email = :email and
                phone_number = :phoneNumber and
                age = :age
            """)
    Optional<Coach> findCoachByFields(String lastname, String firstname, String surname,
                                      String email, String phoneNumber, int age);
}