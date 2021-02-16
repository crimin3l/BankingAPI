package com.example.banking.api.controller;

import com.example.banking.api.domain.dao.Statement;
import com.example.banking.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statement")
public class StatementController {

    private final StatementService statementService;

    @Autowired
    public StatementController(
            final StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/getStatementByCustomerId")
    ResponseEntity<?> getStatementByCustomerId(@RequestParam("customerId") Long customerId) {
        Statement statement = statementService.getStatementByCustomerId(customerId);
        return statement != null ?
                new ResponseEntity<>(statement, HttpStatus.OK) : new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
