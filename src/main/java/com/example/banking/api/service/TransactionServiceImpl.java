package com.example.banking.api.service;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import com.example.banking.api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private CustomerRepository customerRepo;
    private TransactionRepository transactionRepo;
    private AccountRepository accountRepo;

    @Autowired
    public TransactionServiceImpl(
            final CustomerRepository customerRepo,
            final TransactionRepository transactionRepo,
            final AccountRepository accountRepo) {
        this.customerRepo = customerRepo;
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public Transaction createNewTransaction(final Long accountId,final double amount) {
        Optional<Account> accountOpt = accountRepo.findById(accountId);
        Account account = null;
        if (accountOpt.isPresent()) {
            account = accountOpt.get();
            Transaction newTransaction = new Transaction(amount, account);
            transactionRepo.save(newTransaction);
            log.info("Transaction created: {}", newTransaction);
            return newTransaction;
        } else {
            log.info("Creating transaction failed due to non existent accountID");
            return null;
        }
    }
}