package com.example.banking.api.controller;

import com.example.banking.api.domain.Customer;
import com.example.banking.api.domain.Statement;
import com.example.banking.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/statement")
public class StatementController {

    private final CustomerService customerService;
    private final StatementService statementService;

    @Autowired
    public StatementController(
            final CustomerService customerService,
            final StatementService statementService) {
        this.customerService = customerService;
        this.statementService = statementService;
    }
    //TODO: remove getCustomerById method since it is not required in API specifications
    @GetMapping("/getCustomerById/{customerId}")
    ResponseEntity<Customer> getCustomerById(@RequestParam("customerId") Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping("/getStatementByCustomerId/{customerId}")
    ResponseEntity<Statement> getCustomerDetailsById(@RequestParam("customerId") Long customerId) {
        return ResponseEntity.ok(statementService.getStatementByCustomerId(customerId));
    }

}
