package com.example.leon.ewallet.service;


import com.example.leon.ewallet.dao.AccountDAO;
import com.example.leon.ewallet.dao.TransactionDAO;
import com.example.leon.ewallet.entity.Account;
import com.example.leon.ewallet.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


//    @Transactional
//    public List<Account> findAll() {
//        return accountDAO.findAll();
//    }
//
//    @Transactional
//    public Account findById(int id) {
//        return accountDAO.findById(id);
//    }
//
//    @Transactional
//    public Account findByEmail(String email) {
//        return accountDAO.findByEmail(email);
//    }

    @Transactional
    public void save(Transaction transaction) {
        transactionDAO.save(transaction);
    }

}