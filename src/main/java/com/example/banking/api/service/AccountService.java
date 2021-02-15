package com.example.banking.api.service;

import com.example.banking.api.domain.Account;

import java.util.List;

public interface AccountService {
    public Account createNewAccount(final Long customerId);
    Account createNewAccountWithInitialCredit(final Long customerId, final double initialCredidt);
    Account getAccountById(final Long accountId);
    List<Account> getAccountsByBalance(final Double balance);
    void deleteAccountById(final Long accountID);
    void deleteAllAccountsByUserId(final Long customerId);

    List<Account> getAccountsByCustomerId(Long customerId);
}
