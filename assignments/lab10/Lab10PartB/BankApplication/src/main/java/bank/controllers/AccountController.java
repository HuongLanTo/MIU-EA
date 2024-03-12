package bank.controllers;

import bank.domain.AccountEntry;
import bank.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import bank.domain.Account;
import bank.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(
                accountService.createAccount(accountDto.getAccountNumber(), accountDto.getCustomerName()),
                HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AccountResponse> deposit(@RequestBody DepositDto depositDto,
                                                   @RequestParam(required = false) Currency currency) {
        if (currency == Currency.EUR) {
            accountService.depositEuros(depositDto.getAccountNumber(), depositDto.getAmount());
        } else {
            accountService.deposit(depositDto.getAccountNumber(), depositDto.getAmount());
        }
        Account account = accountService.getAccount(depositDto.getAccountNumber());
        return new ResponseEntity<>(accountMapper(account), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AccountResponse> withdraw(@RequestBody DepositDto depositDto,
                                                    @RequestParam(required = false) Currency currency) {
        if (currency == Currency.EUR) {
            accountService.withdrawEuros(depositDto.getAccountNumber(), depositDto.getAmount());
        } else {
            accountService.withdraw(depositDto.getAccountNumber(), depositDto.getAmount());
        }
        Account account = accountService.getAccount(depositDto.getAccountNumber());
        return new ResponseEntity<>(accountMapper(account), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AccountResponse> transferFund(@RequestBody TransferFundDto transferFundDto) {
        accountService.transferFunds(transferFundDto.getFromAccountNumber(), transferFundDto.getToAccountNumber(),
                transferFundDto.getAmount(), transferFundDto.getDescription());

        Account account = accountService.getAccount(transferFundDto.getFromAccountNumber());
        return new ResponseEntity<>(accountMapper(account), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable long accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        return new ResponseEntity<>(accountMapper(account), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts().stream().map(account -> accountMapper(account)).toList(), HttpStatus.OK);
    }
    private AccountResponse accountMapper(Account account) {
        return new AccountResponse(account.getAccountNumber(), account.getCustomer().getName(), account.getEntryList());
    }
}
