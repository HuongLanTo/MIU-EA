package bank.service;

import bank.domain.Account;
import bank.domain.AccountEntry;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {

    public static Account getAccountFromAccountResponse(AccountResponse accountResponse){
        Account account = new Account();
        account.setAccountnumber(accountResponse.getAccountnumber());
        account.setCustomer(CustomerAdapter.getCustomerFromCustomerResponse(accountResponse.getCustomer()));
        for (AccountEntryResponse accountEntryResponse : accountResponse.getEntryList()){
            account.getEntryList().add(AccountEntryAdapter.getAccountEntryFromAccountEntryResponse(accountEntryResponse));
        }
        return account;
    }

    public static AccountResponse getAccountResponseFromAccount(Account account){
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountnumber(account.getAccountnumber());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setCustomer(CustomerAdapter.getCustomerResponseFromCustomer(account.getCustomer()));
        for (AccountEntry accountEntry : account.getEntryList()){
            accountResponse.getEntryList().add(AccountEntryAdapter.getAccountEntryResponseFromAccountEntry(accountEntry));
        }
        return accountResponse;
    }

    public static List<AccountResponse> getAccountResponseListFromAccountList(List<Account> accountList){
        List<AccountResponse> accountResponseList = new ArrayList<>();
        for (Account account : accountList){
            accountResponseList.add(getAccountResponseFromAccount(account));
        }
        return accountResponseList;
    }
}
