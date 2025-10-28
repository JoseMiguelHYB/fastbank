// CONTROLADOR

package com.josemiguelhyb.fastbank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josemiguelhyb.fastbank.dto.CreateUserRequest;
import com.josemiguelhyb.fastbank.dto.UserResponse;
import com.josemiguelhyb.fastbank.mapper.UserMapper;
import com.josemiguelhyb.fastbank.model.User;
import com.josemiguelhyb.fastbank.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;

	// No hace falta @Autowired porque hay solo un constructor
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// Endpoint de prueba
	@GetMapping("/test")
	public String testEndpoint() {
        return "✅ El módulo de usuarios de FastBank funciona correctamente.";
	}
	
	// Obetener todos los usuarios
	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAlllUsers().stream()
				.map(UserMapper::toResponse)
				.collect(Collectors.toList());
	}
	
	// Obtener un usuario por ID
	/***@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}***/
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		return userService.getUserById(id)
				.map(user -> ResponseEntity.ok(UserMapper.toResponse(user)))
				.orElse(ResponseEntity.notFound().build());
	}	
		
	// Crear un nuevo usuario
	/***@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = userService.createUser(user);
		return ResponseEntity.ok(newUser);		
	}***/
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		User newUser = userService.createUser(user);
		return ResponseEntity.ok(UserMapper.toResponse(newUser));	
	}	
}