package cl.escalab.crochicat.controller;

import java.util.List;
import java.util.UUID;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.service.UserService;
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
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(){
		List<User> lista = userService.getAll();
		return new ResponseEntity<List<User>>(lista, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user, @RequestParam int id){
		User userSaved = userService.saveUser(user, id);
		return new ResponseEntity<>(userSaved, HttpStatus.OK);
	}
	
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
