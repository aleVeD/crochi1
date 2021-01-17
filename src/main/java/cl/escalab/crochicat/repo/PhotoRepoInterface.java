package cl.escalab.crochicat.repo;
import cl.escalab.crochicat.dto.SavePhotoDto;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotoRepoInterface extends JpaRepository<Photo, UUID> {
}
