package bank.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TraceRecordRepository;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TraceRecordRepository traceRecordRepository;

	@Autowired
	private EmailSender emailSender;
	
	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber, boolean err) throws Exception {
			try {
				Account account = new Account(AccountNumber);
				accountRepository.save(account);
				Customer customer = new Customer(customerId, customerName);
				customer.setAccount(account);
				customerRepository.saveCustomer(customer);
				LocalDateTime current = LocalDateTime.now();
				traceRecordRepository.save(new TraceRecord(current.toLocalDate(), current.toLocalTime(), "Could not create customer " + customerName + " with account " + AccountNumber));
				emailSender.sendEmail(emailAddress, "Welcome "+customerName);
			} catch(Exception e) {
				LocalDateTime current = LocalDateTime.now();
				traceRecordRepository.save(new TraceRecord(current.toLocalDate(), current.toLocalTime(), "Customer " + customerName + "created with account " + AccountNumber));
				emailSender.sendEmail(emailAddress, "We could not create your account "+AccountNumber);
				if (err) {
					throw new RuntimeException();
				}
			}
	}


}
