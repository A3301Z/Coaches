package Coaches.persistence.repository;

import Coaches.persistence.entity.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoachPhotoRepository extends CrudRepository<Coach, UUID> {
}
