// Aquí la capa es la que contiene la lógica de negocio
package com.josemiguelhyb.fastbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Es está

import com.josemiguelhyb.fastbank.model.User;
import com.josemiguelhyb.fastbank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}	
	
	@Override
	public List<User> getAlllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional // Solo aquí, porque modifica la BD se hace en una operación ÚNICA
	public User createUser(User user) {
		// Lógica de negocio:
		// 1. Verificar si ya existe un usuario con ese email
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser.isPresent()) {
			throw new IllegalArgumentException("El email ya está registrado.");
		}
		
		// 2. Guargar el usuario si pasa las validaciones
		return userRepository.save(user);
	}
}
