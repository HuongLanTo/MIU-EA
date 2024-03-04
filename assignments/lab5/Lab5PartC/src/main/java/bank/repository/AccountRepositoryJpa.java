package bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.domain.Account;

public interface AccountRepositoryJpa extends JpaRepository<Account,Integer> {
	public Account findByAccountNumber(long accountNumber);
}
