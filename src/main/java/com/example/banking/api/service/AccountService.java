package com.example.banking.api.service;

import com.example.banking.api.domain.Account;

public interface AccountService {
    public Account createNewAccout(final Long customerId);
    public void deleteAcountById(final Long accountID);
    void deleteAllAccountsByUserId(final Long customerId);
    Account createNewAccoutWithInitialCredit(final Long customerId, final double initialCredidt);
}
