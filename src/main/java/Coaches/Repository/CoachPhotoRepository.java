package Coaches.Repository;

import Coaches.Entity.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoachPhotoRepository extends CrudRepository<Coach, UUID> {
}
