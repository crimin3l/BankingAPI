package com.example.banking.api.service;

import com.example.banking.api.domain.Customer;
import com.example.banking.api.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepo;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer createNewCustomer(String name, String surname) {
        Customer newCustomer = new Customer(name, surname);
        customerRepo.save(newCustomer);
        return newCustomer;
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        Customer customer = null;
        if (customerOpt.isPresent()) {
            customer = customerOpt.get();
        }
        return customer;
    }

    @Override
    public void deleteCustomerByNameAmdSurname(String name, String surname) {
        List<Customer> customerList = customerRepo.findByNameAndSurname(name, surname);
        customerList
                .stream()
                .forEach(c -> {
                    log.info("Customer deleted: {}", c);
                    customerRepo.deleteById(c.getId());
                });
    }
}