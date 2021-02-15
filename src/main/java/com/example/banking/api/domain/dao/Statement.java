package com.example.banking.api.domain.dao;

import com.example.banking.api.domain.Transaction;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
