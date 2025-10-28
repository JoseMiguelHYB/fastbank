package com.josemiguelhyb.fastbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.fastbank.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
