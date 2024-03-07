package bank.service;

import java.util.Collection;


public interface AccountService {
    AccountResponse createAccount(long accountNumber, String customerName);
    AccountResponse getAccount(long accountNumber);
    Collection<AccountResponse> getAllAccounts();
    void deposit (long accountNumber, double amount);
    void withdraw (long accountNumber, double amount);
    void depositEuros (long accountNumber, double amount);
    void withdrawEuros (long accountNumber, double amount);
    void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
}
