package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.model.ResetToken;
import cl.escalab.crochicat.repo.IResetTokenRepo;
import cl.escalab.crochicat.service.IResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResetTokenServiceImpl implements IResetTokenService {

	@Autowired
	private IResetTokenRepo repo;

	@Override
	public ResetToken findByToken(String token) {
		return repo.findByToken(token);
	}

	@Override
	public void guardar(ResetToken token) {
		repo.save(token);

	}

	@Override
	public void eliminar(ResetToken token) { 
		repo.delete(token);
	}

}
