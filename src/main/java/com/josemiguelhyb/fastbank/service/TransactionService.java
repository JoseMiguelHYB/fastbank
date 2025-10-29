package com.josemiguelhyb.fastbank.service;

import java.math.BigDecimal;
import java.util.List;

import com.josemiguelhyb.fastbank.model.Account;
import com.josemiguelhyb.fastbank.model.Transaction;

public interface TransactionService {
	Transaction deposit(Long accountId, BigDecimal amount);
	Transaction withdraw(Long accountId, BigDecimal amount);
	Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
	List<Transaction> getTransactionsByAccount(Account accountId);
}
