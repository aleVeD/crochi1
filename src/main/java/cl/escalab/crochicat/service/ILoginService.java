package cl.escalab.crochicat.service;

import cl.escalab.crochicat.model.User;

public interface ILoginService {
	
	User verificarNombreUsuario(String usuario) throws Exception;
	int cambiarClave(String clave, String nombre) throws Exception;
}
