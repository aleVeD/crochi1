package cl.escalab.crochicat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cl.escalab.crochicat.model.ResetToken;
import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.service.ILoginService;
import cl.escalab.crochicat.service.IResetTokenService;
import cl.escalab.crochicat.util.EmailUtil;
import cl.escalab.crochicat.util.Mail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private ILoginService service;
	
	@Autowired
	private IResetTokenService tokenService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;


	@PostMapping(value = "/enviarCorreo", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Integer> enviarCorreo(@RequestBody String correo) {
		int rpta = 0;
		try {
			User us = service.verificarNombreUsuario(correo);
			if (us != null && us.getIdUser() != null) {
				
				ResetToken token = new ResetToken();
				token.setToken(UUID.randomUUID().toString());
				token.setUser(us);
				token.setExpiracion(10);
				tokenService.guardar(token);
				
				Mail mail = new Mail();
				mail.setFrom("email.prueba.demo@gmail.com");
				mail.setTo(us.getEmail());
				mail.setSubject("RESTABLECER CONTRASEÑA - MEDIAPP");
				
				Map<String, Object> model = new HashMap<>();
				String url = "http://localhost:4200/recuperar/" + token.getToken();
				model.put("user", token.getUser().getEmail());
				model.put("resetUrl", url);
				mail.setModel(model);
				emailUtil.enviarMail(mail);
				rpta = 1;
			}
		} catch(Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/restablecer/verificar/{token}")
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token) {
		int rpta = 0;
		try {
			if (token != null && !token.isEmpty()) {
				ResetToken rt = tokenService.findByToken(token);
				if (rt != null && rt.getId() > 0) {
					if (!rt.estaExpirado()) {
						rpta = 1;
					}
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	@ApiOperation(value = "restablecer un token",
			response = List.class,
			responseContainer = "ResetToken")
	@ApiResponses(value = {@ApiResponse(code= 400, message = "comentario no enviado"),
			@ApiResponse(code = 404, message = "Comentario no encontrada"),
			@ApiResponse(code = 200, message = "Comentario actualizado exitosamente")})
	@PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token, @RequestBody String clave ) {
		int rpta = 0;
		try {
			ResetToken rt = tokenService.findByToken(token);
			String claveHash = bcrypt.encode(clave);
			rpta = service.cambiarClave(claveHash, rt.getUser().getEmail());
			tokenService.eliminar(rt);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
}
