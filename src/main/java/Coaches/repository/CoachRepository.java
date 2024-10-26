package Coaches.repository;

import Coaches.persistence.entity.Coach;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoachRepository extends CrudRepository<Coach, UUID> {

    @Query("select * from coaches")
    List<Coach> findAll();

    @Query("update is_archived from coaches where id = :id")
    ResponseEntity<?> updateArchivingStatus(UUID id);
}