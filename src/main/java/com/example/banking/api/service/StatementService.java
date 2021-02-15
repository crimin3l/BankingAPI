package com.example.banking.api.service;

import com.example.banking.api.domain.Statement;

public interface StatementService {
    public Statement getStatementByCustomerId(final Long customerId);
}
