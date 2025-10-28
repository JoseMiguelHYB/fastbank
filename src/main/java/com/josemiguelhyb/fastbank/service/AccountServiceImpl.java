package com.josemiguelhyb.fastbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.josemiguelhyb.fastbank.model.Account;
import com.josemiguelhyb.fastbank.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}	

	@Override
	@Transactional
	public Account createAccount(Account account) {
		// 1 - Validar si ya existe una cuenta con el mismo número
		
		Optional<Account> existingAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
		if(existingAccount.isPresent()) {
            throw new IllegalArgumentException("⚠️ Ya existe una cuenta con el número: " + account.getAccountNumber());
		}
				
		// 2 - Guardar la nueva cuenta
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
}
