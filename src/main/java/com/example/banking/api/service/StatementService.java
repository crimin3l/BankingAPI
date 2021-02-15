package com.example.banking.api.service;

import com.example.banking.api.domain.dao.Statement;

public interface StatementService {
    public Statement getStatementByCustomerId(final Long customerId);
}
