package cl.escalab.crochicat.controller;

import java.util.List;
import java.util.UUID;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> listar(){
		List<User> lista = userService.getAll();
		return new ResponseEntity<List<User>>(lista, HttpStatus.OK);
	}
	@ApiOperation(value = "Obtiene todos los usuarios",
			response = List.class,
			responseContainer = "User")
	@ApiResponses(value = {@ApiResponse(code= 400, message = "usuarios no obtenidos"),
			@ApiResponse(code = 404, message = "Usuario no encontrada"),
			@ApiResponse(code = 200, message = "Lista de usuarios  exitosa")})
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(){
		List<User> lista = userService.getAll();
		return new ResponseEntity<List<User>>(lista, HttpStatus.OK);
	}
	@ApiOperation(value = "guardar un usuario",
			response = List.class,
			responseContainer = "Photo")
	@ApiResponses(value = {@ApiResponse(code= 400, message = "usuario no se pudo guardar"),
			@ApiResponse(code = 404, message = "Usuario no encontrado"),
			@ApiResponse(code = 200, message = "Usuario guardada  exitosamente")})
	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user, @RequestParam int id){
		User userSaved = userService.saveUser(user, id);
		return new ResponseEntity<>(userSaved, HttpStatus.OK);
	}

	@ApiOperation(value = "obtener un usuario",
			response = List.class,
			responseContainer = "Photo")
	@ApiResponses(value = {@ApiResponse(code= 400, message = "usuario no se pudo obtener"),
			@ApiResponse(code = 404, message = "Usuario no encontrado"),
			@ApiResponse(code = 200, message = "Usuario encontrado  exitosamente")})
	@GetMapping("/{id}")
	public ResponseEntity<User> listarPorId(@PathVariable("id") UUID id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			User obj = userService.get(id);
			if(obj.getIdUser() == null) {
				throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<User>(obj, HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
		}
		
	}

}
