package com.example.banking.api.service;
import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Customer;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private CustomerRepository customerRepo;
    private AccountRepository accountRepo;

    @Autowired
    public AccountServiceImpl(
            final CustomerRepository customerRepo,
            final AccountRepository accountRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createNewAccoutWithInitialCredit(final Long customerId, final double initialCredit) {
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Customer customer = null;
        if (customerOpt.isPresent()) {
            customer = customerOpt.get();

            // TODO make a transaction
            Transaction transaction = null;

            Account newAccount = new Account(customer, initialCredit, initialCredit);
            accountRepo.save(newAccount);
            log.info("Account created: {}", newAccount);
            return newAccount;
        } else {
            log.info("Creating account failed due to non existent customerID");
            return null;
        }
    }

    @Override
    public Account createNewAccout(final Long customerId) {
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Customer customer = null;
        if (customerOpt.isPresent()) {
            customer = customerOpt.get();
            Account newAccount = new Account(customer, 0, 0);
            accountRepo.save(newAccount);
            log.info("Account created: {}", newAccount);
            return newAccount;
        } else {
            log.info("Creating account failed due to non existent customerID");
            return null;
        }
    }

    @Override
    public void deleteAcountById(final Long accountID) {
        Optional<Account> accountOpt = accountRepo.findById(accountID);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            accountRepo.deleteById(accountID);
            log.info("Account deleted: {}", account);
        } else {
            log.info("Fount no account with ID: {}", accountID);
        }
    }

    @Override
    public void deleteAllAccountsByUserId(final Long customerId) {
        List<Account> accountList = accountRepo.findByCustomerId(customerId);
        accountList
                .stream()
                .forEach(a -> {
                    log.info("Account deleted: {}", a);
                    accountRepo.deleteById(a.getId());
                });
    }
}
