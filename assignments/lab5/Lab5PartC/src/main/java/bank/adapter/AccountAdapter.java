package bank.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.dto.CustomerDTO;

@Component
public class AccountAdapter {
	public Account getAccountFromAccountDTO(AccountDTO accountDTO){
		Account account = new Account(accountDTO.getAccountNumber());
		account.setEntryList(accountDTO.getEntryList());
		Customer customer = new Customer(accountDTO.getCustomer().getName());
		account.setCustomer(customer);
		return account;
	}
	
	public AccountDTO getAccountDTOFromAccount(Account account){
		AccountDTO accountDTO = new AccountDTO(account.getAccountNumber());
		CustomerDTO customerDto = new CustomerDTO(account.getCustomer().getName());
		accountDTO.setCustomer(customerDto);
		accountDTO.setEntryList(account.getEntryList());
		return accountDTO;
	}
	
	public Collection<AccountDTO> getAccountDTOsFromAccounts(Collection<Account> collection) {
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		for (Account account: collection){
			accountDTOs.add(getAccountDTOFromAccount(account)); 
		}
		return accountDTOs; 
	}
	
	public Collection<Account> getAccountsFromAccountDTOs(List<AccountDTO> accountDTOs) {
		List<Account> accounts = new ArrayList<Account>();
		for (AccountDTO accountDTO: accountDTOs){
		accounts.add(getAccountFromAccountDTO(accountDTO)); }
		return accounts; 
	}
}
