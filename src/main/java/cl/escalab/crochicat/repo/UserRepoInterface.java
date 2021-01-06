package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepoInterface extends JpaRepository<User, UUID> {
    User findOneByUsername(String username);
}
