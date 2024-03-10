package bank.service.Impl;

import bank.adapter.AccountAdapter;
import bank.dao.AccountDAO;
import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.jms.JMSSender;
import bank.logging.Logger;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import bank.service.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private CurrencyConverter currencyConverter;
	@Autowired
	private JMSSender jmsSender;
	@Autowired
	private Logger logger;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	private AccountAdapter accountAdapter;

	public AccountDTO createAccount(Long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
//		accountDAO.saveAccount(account);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return accountAdapter.entityToDto(accountRepository.findByAccountnumber(accountNumber));
	}

	public void deposit(Long accountNumber, double amount) {
//		Account = accountDAO.loadAccount(accountNumber);
		Account account = accountRepository.findByAccountnumber(accountNumber);
		account.deposit(amount);
//		accountDAO.updateAccount(account);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public AccountDTO getAccount(Long accountNumber) {
//		Account account = accountDAO.loadAccount(accountNumber);
		Account account = accountRepository.findByAccountnumber(accountNumber);
		return accountAdapter.entityToDto(account);
	}

	public Collection<AccountDTO> getAllAccounts() {
		return accountAdapter.entitiesToAccountDTOs(accountRepository.findAll());
	}

	public void withdraw(Long accountNumber, double amount) {
//		Account account = accountDAO.loadAccount(accountNumber);
		Account account = accountRepository.findByAccountnumber(accountNumber);
		account.withdraw(amount);
//		accountDAO.updateAccount(account);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(Long accountNumber, double amount) {
//		Account account = accountDAO.loadAccount(accountNumber);
		Account account = accountRepository.findByAccountnumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
//		accountDAO.updateAccount(account);
		accountRepository.save(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(Long accountNumber, double amount) {
//		Account account = accountDAO.loadAccount(accountNumber);
		Account account = accountRepository.findByAccountnumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
//		accountDAO.updateAccount(account);
		accountRepository.save(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(Long fromAccountNumber, Long toAccountNumber, double amount, String description) {
//		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
//		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		Account fromAccount = accountRepository.findByAccountnumber(fromAccountNumber);
		Account toAccount = accountRepository.findByAccountnumber(toAccountNumber);
		System.out.println("----- From -----");
		System.out.println(fromAccount);
		System.out.println("----- To -----");
		System.out.println(toAccount);
		System.out.println(description);
		fromAccount.transferFunds(toAccount, amount, description);
//		accountDAO.updateAccount(fromAccount);
//		accountDAO.updateAccount(toAccount);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
