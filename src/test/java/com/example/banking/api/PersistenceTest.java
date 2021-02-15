package com.example.banking.api;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Customer;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import com.example.banking.api.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.banking.api.domain.Transaction;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersistenceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Test
    public void testPersistence() {
        final String generatedString1 = RandomStringUtils.random(10, true, false);
        final String generatedString2 = RandomStringUtils.random(10, true, false);
        final String generatedString3 = RandomStringUtils.random(10, true, false);
        final String generatedString4 = RandomStringUtils.random(10, true, false);
        final String generatedString5 = RandomStringUtils.random(10, true, false);
        final String generatedString6 = RandomStringUtils.random(10, true, false);
        final double generatedDouble1 = Double.parseDouble(RandomStringUtils.random(3, false, true) + "." + RandomStringUtils.random(3, false, true));
        final double generatedDouble2 = Double.parseDouble(RandomStringUtils.random(3, false, true) + "." + RandomStringUtils.random(3, false, true));

        entityManager.persist(new Customer("generatedString1", "generatedString2" ));
        List<Customer> findCustomerByNameAndSurname = customerRepo.findByNameAndSurname("generatedString1", "generatedString2");
        entityManager.persist(new Account(new Customer("generatedString3", "generatedString4" ).getId(),generatedDouble2, generatedDouble2));
        Optional<Account> findAccountById = accountRepo.findById(1L);
        entityManager.persist(new Transaction(generatedDouble1, new Account(new Customer("generatedString5", "generatedString6" ).getId(),generatedDouble2, generatedDouble2)));
        Optional<Transaction> findTransactionById = transactionRepo.findById(1L);
        assert findCustomerByNameAndSurname.size() >= 1 && findAccountById.get().getInitialCredit() == generatedDouble2 && findTransactionById.get().getAmount() == generatedDouble1;
    }

}
