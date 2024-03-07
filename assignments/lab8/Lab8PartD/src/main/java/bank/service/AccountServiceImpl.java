package bank.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.domain.Account;
import bank.domain.Customer;
import bank.jms.JMSSender;
import bank.logging.Logger;
import bank.repository.AccountRepository;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountDAO;
	@Autowired
	private CurrencyConverter currencyConverter;
	@Autowired
	private JMSSender jmsSender;
	@Autowired
	private Logger logger;



	public AccountResponse createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.save(account);
		logger.log("createAccount with parameters accountNumber= "
				+ accountNumber + " , customerName= " + customerName);
		return AccountAdapter.getAccountResponseFromAccount(account);
	}


	public void deposit(long accountNumber, double amount) {
		Account account = accountDAO.findById(accountNumber).get();
		account.deposit(amount);
		accountDAO.save(account);
		logger.log("deposit with parameters accountNumber= " + accountNumber
				+ " , amount= " + amount);
		if (amount > 10000) {
			jmsSender.sendJMSMessage("Deposit of $ " + amount
					+ " to account with accountNumber= " + accountNumber);
		}
	}

	public AccountResponse getAccount(long accountNumber) {
		Account account = accountDAO.findById(accountNumber).get();
		return AccountAdapter.getAccountResponseFromAccount(account);
	}

	public Collection<AccountResponse> getAllAccounts() {
		List<Account> accountList = accountDAO.findAll();
		return AccountAdapter.getAccountResponseListFromAccountList(accountList);
	}


	public void withdraw(long accountNumber, double amount) {
		Account account = accountDAO.findById(accountNumber).get();
		account.withdraw(amount);
		accountDAO.save(account);
		logger.log("withdraw with parameters accountNumber= " + accountNumber
				+ " , amount= " + amount);
	}


	public void depositEuros(long accountNumber, double amount) {
		Account account = accountDAO.findById(accountNumber).get();
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.save(account);
		logger.log("depositEuros with parameters accountNumber= "
				+ accountNumber + " , amount= " + amount);
		if (amountDollars > 10000) {
			jmsSender.sendJMSMessage("Deposit of $ " + amount
					+ " to account with accountNumber= " + accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountDAO.findById(accountNumber).get();
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.save(account);
		logger.log("withdrawEuros with parameters accountNumber= "
				+ accountNumber + " , amount= " + amount);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber,
							  double amount, String description) {
		Account fromAccount = accountDAO.findById(fromAccountNumber).get();
		Account toAccount = accountDAO.findById(toAccountNumber).get();
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.save(fromAccount);
		accountDAO.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "
				+ fromAccountNumber + " , toAccountNumber= " + toAccountNumber
				+ " , amount= " + amount + " , description= " + description);
		if (amount > 10000) {
			jmsSender.sendJMSMessage("TransferFunds of $ " + amount
					+ " from account with accountNumber= " + fromAccount
					+ " to account with accountNumber= " + toAccount);
		}
	}
}
