package bank.service;

import java.util.Collection;

import bank.dto.AccountDTO;

public interface AccountService {

    public AccountDTO createAccount(long accountNumber, String customerName);

    public AccountDTO getAccount(long accountNumber);

    public Collection<AccountDTO> getAllAccounts();

    public void deposit (long accountNumber, double amount);

    public void withdraw (long accountNumber, double amount);

    public void depositEuros (long accountNumber, double amount);

    public void withdrawEuros (long accountNumber, double amount);

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);

}
