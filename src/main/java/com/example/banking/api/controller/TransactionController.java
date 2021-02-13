package com.example.banking.api.controller;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.domain.dao.TransactionNewDao;
import com.example.banking.api.repository.TransactionRepository;
import com.example.banking.api.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/banking-api")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepo;

    @Autowired
    public TransactionController(
            final TransactionService transactionService,
            final TransactionRepository transactionRepo) {
        this.transactionService = transactionService;
        this.transactionRepo = transactionRepo;
    }

    @PostMapping("/createNewTransaction")
    ResponseEntity<Transaction> createNewTransaction(@RequestBody TransactionNewDao dao) {
        return ResponseEntity.ok(transactionService.createNewTransaction(dao.getAccountId(), dao.getAmount()));
    }
}
