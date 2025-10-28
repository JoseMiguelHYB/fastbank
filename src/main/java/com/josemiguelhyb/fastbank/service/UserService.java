// SERVICIO (Interfaz)
package com.josemiguelhyb.fastbank.service;

import java.util.List;
import java.util.Optional;

import com.josemiguelhyb.fastbank.model.User;

public interface UserService {
	List<User> getAlllUsers();
	Optional<User> getUserById(Long id);
	User createUser(User user);	
}
