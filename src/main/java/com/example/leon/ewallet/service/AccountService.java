package com.example.leon.ewallet.service;


import com.example.leon.ewallet.dao.AccountDAO;
import com.example.leon.ewallet.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService {

    private AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    @Transactional
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Transactional
    public Account findById(int id) {
        return accountDAO.findById(id);
    }

    @Transactional
    public Account findByEmail(String email) {
        return accountDAO.findByEmail(email);
    }

    @Transactional( rollbackFor = SQLException.class)
    public void save(Account account) {
        accountDAO.save(account);
    }

}