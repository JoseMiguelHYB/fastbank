package com.josemiguelhyb.fastbank.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateAccountRequest {
	@NotNull(message = "El ID de usuario no puede ser nulo.")
	private Long userId;
	
	
	@NotNull(message = "El balance inicial es obligatorio.")
	@Positive(message = "El balance inicial debe ser un número positivo.")
	private BigDecimal initialBalance;
		
	// Getters y Setters
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
}
