package bank.repository;

import bank.domain.Account;
import bank.domain.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountNumber(long accountNumber);
}
