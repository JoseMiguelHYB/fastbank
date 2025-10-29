package com.josemiguelhyb.fastbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemiguelhyb.fastbank.model.Account;
import com.josemiguelhyb.fastbank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByAccount(Account account);
}
