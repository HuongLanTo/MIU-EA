package accounts.service;

import static org.assertj.core.api.Assertions.assertThat;

import accounts.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import accounts.domain.Account;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    //Test data
    String accountNumber = "82343";
    double balance = 1299.0;
    String accountHolder = "Lofy";

    @Before
    public void setUp() {
        Account account = new Account(accountNumber,balance,accountHolder);
        Optional<Account> accountOptional= Optional.of(account);
        Mockito.when(accountRepository.findById(accountNumber))
                .thenReturn(accountOptional);
    }

    @Test
    public void whenValidAccountNumberThenAccountShouldBeFound() {
        AccountResponse found = accountService.getAccount(accountNumber);

        assertThat(found.getAccountNumber())
                .isEqualTo(accountNumber);

        assertThat(found.getAccountHolder())
                .isEqualTo(accountHolder);

        assertThat(found.getBalance())
                .isEqualTo(balance);
    }

}
