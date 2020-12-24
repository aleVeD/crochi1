package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CommentRepoInterface extends JpaRepository<Comment, UUID> {
}
