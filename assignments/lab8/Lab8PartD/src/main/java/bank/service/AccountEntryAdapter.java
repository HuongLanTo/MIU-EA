package bank.service;

import bank.domain.AccountEntry;

public class AccountEntryAdapter {

    public static AccountEntry getAccountEntryFromAccountEntryResponse(AccountEntryResponse accountEntryResponse){
        AccountEntry accountEntry = new AccountEntry();
        accountEntry.setDate(accountEntryResponse.getDate());
        accountEntry.setAmount(accountEntryResponse.getAmount());
        accountEntry.setDescription(accountEntryResponse.getDescription());
        accountEntry.setFromAccountNumber(accountEntryResponse.getFromAccountNumber());
        accountEntry.setFromPersonName(accountEntryResponse.getFromPersonName());
        return accountEntry;
    }

    public static AccountEntryResponse getAccountEntryResponseFromAccountEntry(AccountEntry accountEntry){
        AccountEntryResponse accountEntryResponse = new AccountEntryResponse();
        accountEntryResponse.setDate(accountEntry.getDate());
        accountEntryResponse.setAmount(accountEntry.getAmount());
        accountEntryResponse.setDescription(accountEntry.getDescription());
        accountEntryResponse.setFromAccountNumber(accountEntry.getFromAccountNumber());
        accountEntryResponse.setFromPersonName(accountEntry.getFromPersonName());
        return accountEntryResponse;
    }

}
