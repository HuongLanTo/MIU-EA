package bank.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.domain.Account;

@Repository
public class AccountRepositoryImpl implements AccountRepository  {
	@Autowired
	AccountRepositoryJpa accountRepositoryJpa;
	
	public void saveAccount(Account account) {
		accountRepositoryJpa.save(account);
	}
	
	public void updateAccount(Account account) {
		accountRepositoryJpa.save(account);
	}

	public Account loadAccount(long accountNumber) {
		return accountRepositoryJpa.findByAccountNumber(accountNumber);
	}

	public Collection<Account> getAccounts() {
		return accountRepositoryJpa.findAll();
	}

}
