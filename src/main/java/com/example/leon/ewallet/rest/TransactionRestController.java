package com.example.leon.ewallet.rest;


import com.example.leon.ewallet.entity.Transaction;
import com.example.leon.ewallet.response.TransactionResponseHandler;
import com.example.leon.ewallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    public ResponseEntity<Object> transfer(@Valid @RequestBody Transaction transaction) {
        transaction.setType("transfer");
        Map<String, Object> responseBody = new HashMap<String, Object>();
        try {
            transactionService.save(transaction);
            return TransactionResponseHandler.generateSuccessResponse();
        } catch (Exception e) {
            return TransactionResponseHandler.generateErrorResponse(e.getMessage());
        }
    }

    @PostMapping("/latest-transactions")
    public ResponseEntity<Object> getLatestTransactions(@RequestBody Transaction transaction) {
        List<Transaction> latestTransactions = transactionService.findLatestTransactions(transaction.getSender_email());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("transactions", latestTransactions);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}
