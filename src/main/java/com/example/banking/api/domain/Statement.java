package com.example.banking.api.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Statement {
    private String name;
    private String surname;
    private double balance;
    private List<Transaction> transactionList = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
        balance += transaction.getAmount();
    }

}
