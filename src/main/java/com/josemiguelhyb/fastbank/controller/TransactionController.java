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

import com.josemiguelhyb.fastbank.dto.CreateTransactionRequest;
import com.josemiguelhyb.fastbank.dto.TransactionResponse;
import com.josemiguelhyb.fastbank.mapper.TransactionMapper;
import com.josemiguelhyb.fastbank.model.Account;
import com.josemiguelhyb.fastbank.model.Transaction;
import com.josemiguelhyb.fastbank.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	// POST /api/transactions/deposit (INGRESAR DINERO)
	@PostMapping("/deposit")
	public ResponseEntity<TransactionResponse> deposit(@RequestBody CreateTransactionRequest request) {
		Transaction transaction = transactionService.deposit(request.getToAccountId(), request.getAmount());
		return ResponseEntity.ok(TransactionMapper.toResponse(transaction));		
	}
	
	// POST /api/transactions/withedraw (RETIRAR DINERO)
	@PostMapping("/withdraw")
	public ResponseEntity<TransactionResponse> withdraw(@RequestBody CreateTransactionRequest request) {
		Transaction transaction = transactionService.withdraw(request.getFromAccountId(), request.getAmount());
		return ResponseEntity.ok(TransactionMapper.toResponse(transaction));		
	}
	
	// POST /api/transactions/transfer (TRANSFERIR ENTRE CUENTAS)
	@PostMapping("/transfer")
	public ResponseEntity<TransactionResponse> transfer(@RequestBody CreateTransactionRequest request) {
		Transaction transaction = transactionService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
		return ResponseEntity.ok(TransactionMapper.toResponse(transaction));		
	}
		
	// POST /api/transactions/account/{accountId} (LISTAR TRANSACCIONES DE UNA CUENTA)
	@GetMapping("/account/{accountId}")
	public List<TransactionResponse> getTransactionsByAccount(@PathVariable Account accountId) {
		return transactionService.getTransactionsByAccount(accountId)
				.stream()
				.map(TransactionMapper::toResponse)
				.collect(Collectors.toList());		
	}
}
