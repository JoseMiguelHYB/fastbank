package com.josemiguelhyb.fastbank.dto;

import java.math.BigDecimal;

public class CreateAccountRequest {
	private Long userId;
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
