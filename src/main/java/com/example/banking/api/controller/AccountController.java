package com.example.banking.api.controller;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.dao.AccountNewDao;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.TransactionRepository;
import com.example.banking.api.service.AccountService;
import com.example.banking.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/banking-api")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepo;

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepo;

    @Autowired
    public AccountController(
            final AccountService accountService,
            final AccountRepository accountRepo,
            final TransactionService transactionService,
            final TransactionRepository transactionRepo) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
        this.transactionService = transactionService;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping("/getAccountById/{accountId}")
    ResponseEntity<Account> getAccountById(@RequestParam("accountId") Long accountId) {
        Optional<Account> accountOpt = accountRepo.findById(accountId);
        if (accountOpt.isPresent()) {
            return ResponseEntity.ok(accountOpt.get());
        }
        return null;
    }

    @PostMapping("/createNewAccout")
    ResponseEntity<Account> createNewAccout(@RequestBody AccountNewDao dao) {
        Account newAccount = accountService.createNewAccoutWithInitialCredit(dao.getCustomerId(), dao.getInitialCredit());
        if(dao.getInitialCredit() != 0) {
            transactionService.createNewTransaction(newAccount.getId(), dao.getInitialCredit());
        }
        return ResponseEntity.ok(newAccount);
    }

}