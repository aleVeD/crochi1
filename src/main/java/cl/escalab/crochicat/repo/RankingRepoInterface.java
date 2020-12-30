package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RankingRepoInterface extends JpaRepository<Ranking, UUID> {
}
