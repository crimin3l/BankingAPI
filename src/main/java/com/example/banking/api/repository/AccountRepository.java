package com.example.banking.api.repository;

import com.example.banking.api.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;



public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findById(final Long id);
    List<Account> findByCustomerId(final Long customerId);
    List<Account> findByBalance(Double balance);
}
