package com.example.banking.api.repository;

import com.example.banking.api.domain.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByNameAndSurname(String name, String surname);
    Optional<Customer> findById(Long id);
    List<Customer> findTop5ByName(String name);
    List<Customer> findTop5BySurname(String surname);
}
