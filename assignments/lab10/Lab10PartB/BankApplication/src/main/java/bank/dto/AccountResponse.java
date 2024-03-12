package bank.dto;

import java.util.ArrayList;
import java.util.List;

import bank.domain.AccountEntry;

public class AccountResponse {

    private long accountNumber;
    private String customerName;
    private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

    public AccountResponse(long accountNumber, String customerName, List<AccountEntry> entryList) {
        super();
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.entryList = entryList;
    }

    public AccountResponse() {
        super();
    }

    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : entryList) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<AccountEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<AccountEntry> entryList) {
        this.entryList = entryList;
    }

}
