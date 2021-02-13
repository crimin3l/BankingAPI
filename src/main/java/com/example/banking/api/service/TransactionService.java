package com.example.banking.api.service;

import com.example.banking.api.domain.Transaction;

public interface TransactionService {
    public Transaction createNewTransaction(final Long accountId, final double amount);

}
