package com.josemiguelhyb.fastbank.service;

import java.util.List;
import java.util.Optional;

import com.josemiguelhyb.fastbank.model.Account;

public interface AccountService {
	List<Account> getAllAccounts();
	Optional<Account> getAccountById(Long id);
	Account createAccount(Account account);
}
