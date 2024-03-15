package accounts.controller;

import accounts.domain.Account;
import accounts.service.AccountResponse;
import accounts.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class AccountControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    AccountService accountService;

    //Test data
    String accountNumber = "82343";
    double balance = 1299.0;
    String accountHolder = "Lofy";

    Account account = new Account(accountNumber, balance, accountHolder);
    AccountResponse accountResponse = new AccountResponse(accountNumber, balance, accountHolder);

    @Test
    void testGetAccountByAccountNumber() throws Exception {
        Mockito.when(accountService.getAccount(accountNumber)).thenReturn(accountResponse);
        mock.perform(get("/account/82343"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value(accountNumber))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(balance))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolder").value(accountHolder));
    }

    @Test
    public void testCreateAccount() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/createaccount/"+82343+"/"+1299.0+"/"+"Lofy")
                        .content(asJsonString(account))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(accountService, times(1)).createAccount(accountNumber, balance, accountHolder);

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}