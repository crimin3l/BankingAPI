package com.example.banking.api.controller;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.dao.AccountNewDao;

import com.example.banking.api.service.AccountService;
import com.example.banking.api.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(
            final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createNewAccount")
    ResponseEntity<Account> createNewAccount(@RequestBody AccountNewDao dao) {
        Account newAccount = accountService.createNewAccountWithInitialCredit(dao.getCustomerId(), dao.getInitialCredit());
        return ResponseEntity.ok(newAccount);
    }
}