package com.example.banking.api.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private long id;


    private long customerId;

    private double initialCredit;
    private double balance;

    // Empty constructor for JSON/JPA
    public Account() {
        this(0L, 0L, 0D);
    }

    public Account(Long customerId, double initialCredit, double balance) {
        this.customerId = customerId;
        this.initialCredit = initialCredit;
        this.balance = balance;
    }
}
