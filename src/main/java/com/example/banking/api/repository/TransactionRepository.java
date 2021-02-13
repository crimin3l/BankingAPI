package com.example.banking.api.repository;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Transaction;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);
    List<Transaction> findByAccountId(final Long accountId);
}

