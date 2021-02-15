package com.example.banking.api.service;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.Customer;
import com.example.banking.api.domain.Transaction;
import com.example.banking.api.repository.AccountRepository;
import com.example.banking.api.repository.CustomerRepository;
import com.example.banking.api.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    private AccountServiceImpl accountServiceImpl;

    @Mock
    private CustomerRepository customerRepo;
    @Mock
    private AccountRepository accountRepo;
    @Mock
    private TransactionRepository transactionRepo;

    @Before
    public void Setup() {
        MockitoAnnotations.openMocks(this);
        accountServiceImpl = new AccountServiceImpl(customerRepo, accountRepo, transactionRepo);

    }

    @Test
    public void createNewAccountWithInitialCreditTest() throws JSONException {

        String jsonString = new JSONObject()
                .put("id", 1)
                .put("name", "TestName")
                .put("surname", "TestSurname").toString();

        Gson g = new Gson();
        Customer c = g.fromJson(jsonString, Customer.class);

        Optional<Customer> newCustomer = Optional.of(new Customer());
        when(customerRepo.findById(any())).thenReturn(Optional.ofNullable(c));

        Optional<Account> newAccount = Optional.of(new Account());
        given(accountRepo.findById(1L)).willReturn(newAccount);

        Optional<Transaction> newTransaction = Optional.of(new Transaction());
        given(transactionRepo.findById(1L)).willReturn(newTransaction);

        Account myTestAccount = accountServiceImpl.createNewAccountWithInitialCredit(1L, 1D );

        assertThat(myTestAccount.getInitialCredit()).isEqualTo(1D);
        assertThat(myTestAccount.getCustomerId()).isEqualTo(1L);

        Account myNewAccount = new Account(1L, 1L, 1D);
        verify(accountRepo).save(myNewAccount);


    }
}
