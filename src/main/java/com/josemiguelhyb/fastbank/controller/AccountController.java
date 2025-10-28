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

import com.josemiguelhyb.fastbank.dto.AccountResponse;
import com.josemiguelhyb.fastbank.dto.CreateAccountRequest;
import com.josemiguelhyb.fastbank.mapper.AccountMapper;
import com.josemiguelhyb.fastbank.model.Account;
import com.josemiguelhyb.fastbank.model.User;
import com.josemiguelhyb.fastbank.service.AccountService;
import com.josemiguelhyb.fastbank.service.UserService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private final AccountService accountService;
	private final UserService userService;
	
	public AccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}
		
	// Crear una cuenta nueva
	@PostMapping
	public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
		User user = userService.getUserById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
		Account account = new Account();
		account.setAccountNumber("ACC- " + System.currentTimeMillis()); 
		account.setBalance(request.getInitialBalance()); // También se inicializa a ZERO no??
		account.setUser(user);
		// No hace falta hacer referencia a createdAt ya que se inicializa al tiempo del sistema.
				
		Account newAccount = accountService.createAccount(account);
		return ResponseEntity.ok(AccountMapper.toResponse(newAccount));		
	}
	
	
    // Obtener una cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(account -> ResponseEntity.ok(AccountMapper.toResponse(account)))
                .orElse(ResponseEntity.notFound().build());
    }
	
	
    // Obtener todas las cuentas
    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts().stream()
                .map(AccountMapper::toResponse)
                .collect(Collectors.toList());
    }	
}
