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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_ID")
    private final Customer customerId;

    private final double initialCredit;
    private final double balance;

    // Empty constructor for JSON/JPA
    public Account() {
        this(null, 0D, 0D);
    }
}
