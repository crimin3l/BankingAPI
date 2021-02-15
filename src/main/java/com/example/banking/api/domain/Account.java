package com.example.banking.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;


    private Long customerId;

    private final double initialCredit;
    private Double balance;

    // Empty constructor for JSON/JPA
    public Account() {
        this(null, 0L, 0D);
    }

    public Account(Long customerId, double initialCredit, Double balance) {
        this.id = id;
        this.customerId = customerId;
        this.initialCredit = initialCredit;
        this.balance = balance;
    }
}
