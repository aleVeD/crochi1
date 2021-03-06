package cl.escalab.crochicat.model;

import cl.escalab.crochicat.model.User;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity

public class ResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = false)
	private String token;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "id_user")
	private User user;
	
	@Column(nullable = false)
	private LocalDateTime expiracion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(LocalDateTime expiracion) {
		this.expiracion = expiracion;
	}
	
	public void setExpiracion(int minutos) {
		LocalDateTime hoy = LocalDateTime.now();
		LocalDateTime exp = hoy.plusMinutes(minutos);
		this.expiracion = exp;
	}
	
	public boolean estaExpirado() {
		return LocalDateTime.now().isAfter(this.expiracion);
	}
}
