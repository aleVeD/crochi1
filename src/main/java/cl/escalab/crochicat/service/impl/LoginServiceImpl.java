package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.repo.ILoginRepo;
import cl.escalab.crochicat.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private ILoginRepo repo;
	
	@Override
	public int cambiarClave(String clave, String nombre) {
		int rpta = 0;
		try {
			repo.cambiarClave(clave, nombre);
			rpta = 1;
		} catch (Exception e) {
			rpta = 0;
		}
		return rpta;
	}
	
	@Override
	public User verificarNombreUsuario(String usuario) throws Exception {
		User us = null;
		try {
			us = repo.verificarNombreUsuario(usuario);
			us = us != null ? us : new User();
		} catch (Exception e) {
			us = new User();
		}
		return us;
	}

}
