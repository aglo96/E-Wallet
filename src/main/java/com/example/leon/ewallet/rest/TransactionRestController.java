package com.example.leon.ewallet.rest;


import com.example.leon.ewallet.entity.Transaction;
import com.example.leon.ewallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionRestController {
    private TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public void transfer(@Valid @RequestBody Transaction transaction) {
//        theAccount.setBalance(10000);
        transaction.setType("transfer");
        transactionService.save(transaction);
//        return AccountResponseHandler.generateResponse(HttpStatus.OK, theAccount);
    }

    @PostMapping("/latest-transactions")
    public ResponseEntity<Object> getLatestTransactions(@RequestBody Transaction transaction) {
        List<Transaction> latestTransactions = transactionService.findLatestTransactions(transaction.getSender_email());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("transactions", latestTransactions);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }


//    @GetMapping("/accounts")
//    public List<Account> findAll() {
//        return accountService.findAll();
//    }
//
//    @GetMapping("/accounts/{accountId}")
//    public Account getAccount(@PathVariable int accountId) {
//        Account account = accountService.findById(accountId);
//        if (account == null) {
//            throw new RuntimeException("Account id not found = " + accountId);
//        }
//        return account;
//    }
//
//    @PostMapping("/balance")
//    public ResponseEntity<Object> getAccountBalance(@Valid @RequestBody Account theAccount) {
//        Account account = accountService.findByEmail(theAccount.getEmail());
//        if (account == null) {
//            throw new RuntimeException("Account email not found = " + account.getEmail());
//        }
//        return AccountResponseHandler.generateResponse(HttpStatus.OK, account);
//    }
//
//    @PostMapping("/accounts")
//    public ResponseEntity<Object> registerAccount(@Valid @RequestBody Account theAccount) {
//        theAccount.setBalance(10000);
//        accountService.save(theAccount);
//        return AccountResponseHandler.generateResponse(HttpStatus.OK, theAccount);
//    }

}
