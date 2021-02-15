package com.example.banking.api.service;

import com.example.banking.api.domain.Statement;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {
    private CustomerRepository customerRepo;
    private AccountRepository accountRepo;

    @Override
    public Statement getStatementByCustomerId(Long customerId) {
        //TODO:  construct Statement object
        return null;
    }
    @Autowired
    public StatementServiceImpl(
            final CustomerRepository customerRepo,
            final AccountRepository accountRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
    }

}
