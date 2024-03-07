package bank.service;

import java.util.ArrayList;
import java.util.Collection;


public class AccountResponse {
	private long accountnumber;
	private double balance;
	private Collection<AccountEntryResponse> entryList = new ArrayList<AccountEntryResponse>();
	private CustomerResponse customer;

	public AccountResponse() {
	}

	public AccountResponse(long accountnr){
		this.accountnumber = accountnr;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public CustomerResponse getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResponse customer) {
		this.customer = customer;
	}
	public Collection<AccountEntryResponse> getEntryList() {
		return entryList;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
