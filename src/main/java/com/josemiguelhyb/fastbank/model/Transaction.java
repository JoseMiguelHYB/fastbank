package com.josemiguelhyb.fastbank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account; // Identificador de la cuenta
	
	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransactionType type; // DEPOSIT, WITHDRAW, TRANSFER
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	// Constructor vacío
	public Transaction() {
	}

	public Transaction(Account accountId, BigDecimal amount, TransactionType type) {
		this.account = accountId;
		this.amount = amount;
		this.type = type;
		this.createdAt = LocalDateTime.now();
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account accountId) {
		this.account = accountId;
	}



	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountId=" + account+ ", amount=" + amount + ", type=" + type
				+ ", createdAt=" + createdAt + "]";
	}
}
