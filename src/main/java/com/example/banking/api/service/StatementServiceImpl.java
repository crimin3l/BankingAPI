package com.example.banking.api.service;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Customer;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.domain.dao.Statement;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import com.example.banking.api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {
    private CustomerRepository customerRepo;
    private AccountRepository accountRepo;
    private TransactionRepository transactionRepo;

    @Override
    public Statement getStatementByCustomerId(Long customerId) {

        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Customer customer = null;
        if (customerOpt.isPresent()) {
            Statement newStatement = new Statement();
            customer = customerOpt.get();
            newStatement.setName(customer.getName());
            newStatement.setSurname(customer.getSurname());

            List<Account> accountList = accountRepo.findByCustomerId(customerId);
            accountList
                    .stream()
                    .forEach(a -> {
                        List<Transaction> transactionList = transactionRepo.findByAccountId(a.getId());

                        log.error("the transaction list for account with id:" + a.getId() + " is:" + transactionList.toString());

                        transactionList
                                .stream()
                                .forEach(t -> {
                                    newStatement.addTransaction(t);
                                });
                    });
            return newStatement;
        } else {
            log.info("Non-existent customerID");
            return null;
        }
    }



    @Autowired
    public StatementServiceImpl(
            final CustomerRepository customerRepo,
            final AccountRepository accountRepo,
            final TransactionRepository transactionRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

}
