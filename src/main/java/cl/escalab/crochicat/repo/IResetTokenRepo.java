package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResetTokenRepo extends JpaRepository<ResetToken, Integer> {
	
	//from ResetToken where token = :?
	ResetToken findByToken(String token);

}

