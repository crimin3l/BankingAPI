package com.example.banking.api.controller;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.domain.dao.AccountNewDao;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.TransactionRepository;
import com.example.banking.api.service.AccountService;
import com.example.banking.api.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/banking-api")
public class UserAccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepo;
    private final TransactionService transactionService;

    @Autowired
    public UserAccountController(
            final AccountService accountService,
            final AccountRepository accountRepo,
            final TransactionService transactionService) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
        this.transactionService = transactionService;
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