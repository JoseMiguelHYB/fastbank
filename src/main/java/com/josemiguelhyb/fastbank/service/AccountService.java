package com.josemiguelhyb.fastbank.service;

import java.util.List;
import java.util.Optional;

import com.josemiguelhyb.fastbank.model.Account;

public interface AccountService {
	Account createAccount(Account account);
	Optional<Account> getAccountById(Long id);
	List<Account> getAllAccounts();
}
