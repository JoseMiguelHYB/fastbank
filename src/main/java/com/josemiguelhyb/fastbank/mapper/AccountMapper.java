package com.josemiguelhyb.fastbank.mapper;

import com.josemiguelhyb.fastbank.dto.AccountResponse;
import com.josemiguelhyb.fastbank.model.Account;

public class AccountMapper {
	public static AccountResponse toResponse(Account account) {
		return new AccountResponse(
					account.getId(),
					account.getAccountNumber(),
					account.getBalance(),
					account.getCreatedAt(),
					account.getUser().getId()				
				);
	}
}
