package com.example.leon.ewallet.rest;


import com.example.leon.ewallet.entity.Account;
import com.example.leon.ewallet.response.RegisterAccountResponseHandler;
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

    @PostMapping("/accounts")
    public ResponseEntity<Object> registerAccount(@Valid @RequestBody Account theAccount) {
        theAccount.setBalance(10000);
        accountService.save(theAccount);
        return RegisterAccountResponseHandler.generateResponse(HttpStatus.OK, theAccount);
    }

}
