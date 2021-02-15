package com.example.banking.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Statement {
    private String name;
    private String surname;
    private Double balance;
    private List<Transaction> transactionList;

}
