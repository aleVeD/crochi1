package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepoInterface extends JpaRepository<Rol, Integer> {
}
