package com.example.banking.api.service;

import com.example.banking.api.domain.Account;

public interface AccountService {
    public Account createNewAccount(final Long customerId);
    public void deleteAccountById(final Long accountID);
    void deleteAllAccountsByUserId(final Long customerId);
    Account createNewAccountWithInitialCredit(final Long customerId, final double initialCredidt);
    Account getAccountById(final Long accountId);
}
