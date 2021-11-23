package com.example.leon.ewallet.service;


import com.example.leon.ewallet.dao.TransactionDAO;
import com.example.leon.ewallet.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {

    private TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Transactional( rollbackFor = SQLException.class)
    public void save(Transaction transaction) {
        transactionDAO.save(transaction);
    }

    @Transactional
    public List<Transaction> findLatestTransactions(String sender_email) {
        return transactionDAO.findLatestTransactions(sender_email);
    }


}