package cl.escalab.crochicat.service;
import cl.escalab.crochicat.model.ResetToken;

public interface IResetTokenService {
	
	ResetToken findByToken(String token);
	
	void guardar(ResetToken token);
	
	void eliminar(ResetToken token);
}
