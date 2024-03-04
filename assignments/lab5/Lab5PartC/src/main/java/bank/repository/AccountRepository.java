package bank.repository;

import java.util.Collection;

import bank.domain.Account;


public interface AccountRepository {

	public void saveAccount(Account account);
	
	public void updateAccount(Account account);

	public Account loadAccount(long accountNumber);

	public Collection<Account> getAccounts();

}
