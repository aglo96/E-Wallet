package com.example.leon.ewallet.dao;

import com.example.leon.ewallet.entity.Account;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class AccountDAO {

    private EntityManager entityManager;

    @Autowired
    public AccountDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Account> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Account> theQuery = currentSession.createQuery("from Account", Account.class);

        List<Account> accounts = theQuery.getResultList();

        return accounts;
    }

    public Account findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Account theAccount = currentSession.get(Account.class, id);
        return theAccount;

    }

    public void save(Account account) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(account);
    }
}
