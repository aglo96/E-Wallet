package com.example.leon.ewallet.rest;


import com.example.leon.ewallet.entity.Account;
import com.example.leon.ewallet.response.AccountResponseHandler;
import com.example.leon.ewallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        Account account = accountService.findById(accountId);
        if (account == null) {
            throw new RuntimeException("Account id not found = " + accountId);
        }
        return account;
    }

    @PostMapping("/balance")
    public ResponseEntity<Object> getAccountBalance(@Valid @RequestBody Account theAccount) {
        Account account = accountService.findByEmail(theAccount.getEmail());
        if (account == null) {
            throw new RuntimeException("Account email not found = " + account.getEmail());
        }
        return AccountResponseHandler.generateResponse(HttpStatus.OK, account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> registerAccount(@Valid @RequestBody Account theAccount) {
        theAccount.setBalance(10000);
        accountService.save(theAccount);
        return AccountResponseHandler.generateResponse(HttpStatus.OK, theAccount);
    }

}
