package accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounts.domain.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, String>{
   Account findByAccountHolder(String accountHolder);

   @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
   Account findByAccountNumber(@Param("accountNumber") String accountNumber);

   @Query("SELECT a FROM Account a WHERE a.balance >= 1000")
   Account findByBalanceGreaterThanEqual1000 ();

}
