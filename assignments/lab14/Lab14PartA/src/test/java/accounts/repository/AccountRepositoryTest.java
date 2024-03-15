package accounts.repository;

import accounts.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    //Test Data
    Account account = new Account("82343", 1299.0, "Lofy");
    Account account1 = new Account("62356", 300.0, "Harry Potter");

    @Test
    public void findByAccountHolder() {
        testEntityManager.persist(account);
        testEntityManager.flush();

        //Return object which was find by provided accountHolder
        Account found = accountRepository.findByAccountHolder(account.getAccountHolder());

        //Expected Result
        String expectedResult = account.getAccountHolder();

        //Actual Result
        String actualResult = found.getAccountHolder();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void findByAccountNumber() {
        testEntityManager.persist(account);
        testEntityManager.flush();

        Account found = accountRepository.findByAccountNumber(account.getAccountNumber());

        String expectedResult = account.getAccountNumber();
        String actualResult = found.getAccountNumber();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void findByBalanceGreaterThanEqual1000() {
        testEntityManager.persist(account);
        testEntityManager.persist(account1);
        testEntityManager.flush();

        Account found = accountRepository.findByBalanceGreaterThanEqual1000();


        double expectedResult = account.getBalance();
        double actualResult = found.getBalance();

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}