package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PostRepoInterface extends JpaRepository<Post, UUID> {
}
