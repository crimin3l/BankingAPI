package com.example.banking.api.domain;

import javax.persistence.*;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    private final double amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ACCOUNT_ID")
    private final Account account;

    // Empty constructor for JSON/JPA
    public Transaction() {
        this(0D, null);
    }
}
