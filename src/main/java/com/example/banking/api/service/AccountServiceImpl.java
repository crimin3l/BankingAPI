package com.example.banking.api.service;
import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Customer;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import com.example.banking.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private CustomerRepository customerRepo;
    private AccountRepository accountRepo;
    private TransactionRepository transactionRepo;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public AccountServiceImpl(
            final CustomerRepository customerRepo,
            final AccountRepository accountRepo,
            final TransactionRepository transactionRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Account getAccountById(final Long accountId) {
        Optional<Account> accountOpt = accountRepo.findById(accountId);
        Account account = null;
        if (accountOpt.isPresent()) {
            account = accountOpt.get();
        }
        return account;
    }


    @Override
    public Account createNewAccountWithInitialCredit(final Long customerId, final double initialCredit) {
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Customer customer = null;
        if (customerOpt.isPresent()) {
            customer = customerOpt.get();
            Account newAccount = new Account(customer.getId(), initialCredit, initialCredit);
            accountRepo.save(newAccount);
            log.info("Account created: {}", newAccount);
            if(initialCredit != 0) {
                Transaction newTransaction = new Transaction(initialCredit, newAccount);
                transactionRepo.save(newTransaction);
            }
            return newAccount;
        } else {
            log.info("Creating account failed due to non existent customerID");
            return null;
        }
    }

    @Override
    public Account createNewAccount(final Long customerId) {
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Customer customer = null;
        if (customerOpt.isPresent()) {
            customer = customerOpt.get();
            Account newAccount = new Account(customer.getId(), 0D, 0D);
            accountRepo.save(newAccount);
            log.info("Account created: {}", newAccount);
            return newAccount;
        } else {
            log.info("Creating account failed due to non existent customerID");
            return null;
        }
    }

    @Override
    public void deleteAccountById(final Long accountID) {
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

    @Override
    public List<Account> getAccountsByBalance(final Double balance) {
        List<Account> accountList = accountRepo.findByBalance(balance);
        return accountList;
    };

    @Override
    public List<Account> getAccountsByCustomerId(final Long customerId) {
        log.error("!!!!!!!!!!!!!!!!!!!!!!");
        List<Account> accountList = accountRepo.findByCustomerId(customerId);
        return accountList;
    };
}
