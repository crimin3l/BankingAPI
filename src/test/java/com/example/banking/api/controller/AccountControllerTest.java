package com.example.banking.api.controller;

import com.example.banking.api.domain.Account;
import com.example.banking.api.domain.dao.AccountNewDao;
import com.example.banking.api.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mvc;

    private AccountNewDao accountDao;

    private JacksonTester<AccountNewDao> jacksonAccountNewDaoTester;
    private JacksonTester<Account> jacksonAccountTester;

    @Before
    public void Setup() {
    JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void createNewAccontTest() throws Exception {

        AccountNewDao myDao = new AccountNewDao();
        myDao.setCustomerId(1L);
        myDao.setInitialCredit(23.5D);

        Account myAccount = new Account();
        myAccount.setBalance(0D);
        myAccount.setId(0);

        given(accountService.createNewAccountWithInitialCredit(1L, 23.5D)).willReturn(myAccount);

        MockHttpServletResponse servletResponse = mvc.
                perform(post("/api/v1/account/createNewAccount").
                contentType(MediaType.APPLICATION_JSON).content(jacksonAccountNewDaoTester.write(myDao).getJson())
                        ).andReturn().getResponse();

        assertThat(servletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(servletResponse.getContentAsString()).isEqualTo(jacksonAccountTester.write(myAccount).getJson());
    }

}
