package com.josemiguelhyb.fastbank.mapper;

import com.josemiguelhyb.fastbank.dto.TransactionResponse;
import com.josemiguelhyb.fastbank.model.Transaction;

public class TransactionMapper {
    public static TransactionResponse toResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setType(transaction.getType().name());
        response.setAmount(transaction.getAmount());
        response.setAccountId(
            transaction.getAccount() != null ? transaction.getAccount().getId() : null
        );
        response.setAccountId(
            transaction.getAccount() != null ? transaction.getAccount().getId() : null
        );
        response.setCreatedAt(transaction.getCreatedAt());
        return response;
    }
}