package com.example.leon.ewallet.dao;

import com.example.leon.ewallet.entity.Account;
import com.example.leon.ewallet.entity.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class TransactionDAO {

    private EntityManager entityManager;

    @Autowired
    public TransactionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Transaction> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Transaction> theQuery = currentSession.createQuery("from Transaction", Transaction.class);

        List<Transaction> transactions = theQuery.getResultList();

        return transactions;
    }

//    public Account findById(int id) {
//        Session currentSession = entityManager.unwrap(Session.class);
//        Account theAccount = currentSession.get(Account.class, id);
//        return theAccount;
//    }
//
//    public Account findByEmail(String email) {
//        Session currentSession = entityManager.unwrap(Session.class);
//        Query query = currentSession.createQuery("from Account where email=:email");
//        query.setParameter("email", email);
//        Account account = (Account) query.uniqueResult();
//        return account;
//    }

    public void save(Transaction transaction) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(transaction);


        //hibenate transactions....
    }
}
