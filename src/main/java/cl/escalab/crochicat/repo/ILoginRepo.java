package cl.escalab.crochicat.repo;

import cl.escalab.crochicat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ILoginRepo extends JpaRepository<User, Integer> {
	
	@Query("FROM User us where us.email = :usuario")
	User verificarNombreUsuario(@Param("usuario") String usuario) throws Exception;
	
	//Usuario findOneByUsername(String usuario)
	
	@Transactional
	@Modifying
	@Query("UPDATE User us SET us.password = :clave WHERE us.email = :nombre")
	void cambiarClave(@Param("clave") String clave, @Param("nombre") String nombre) throws Exception;

}
