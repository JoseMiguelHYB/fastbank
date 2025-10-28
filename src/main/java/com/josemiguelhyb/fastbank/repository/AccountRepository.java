package com.josemiguelhyb.fastbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.fastbank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByAccountNumber(String accountNumber);
	List<Account> findByUserId(Long userId);
}
