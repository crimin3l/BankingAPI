package com.example.banking.api.service;

import com.example.banking.api.domain.Customer;

public interface CustomerService {
    public Customer createNewCustomer(final String name, final String surname);
    public void deleteCustomerById(final Long customerId);
    public void deleteCustomerByNameAmdSurname(final String name, final String surname);
}
