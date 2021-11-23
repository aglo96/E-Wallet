package com.example.leon.ewallet.dao;

import com.example.leon.ewallet.entity.Account;
import com.example.leon.ewallet.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@Repository
public class TransactionDAO {

    private EntityManager entityManager;

    @Autowired
    public TransactionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Transaction transaction) {
        Session session = entityManager.unwrap(Session.class);

        int transfer_amount = transaction.getAmount();
        String sender_email = transaction.getSender_email();
        String receiver_email = transaction.getReceiver_email();

        Query senderAccountQuery = session.createQuery("from Account where email=:email");
        senderAccountQuery.setParameter("email", sender_email);
        Account senderAccount =  (Account) senderAccountQuery.uniqueResult();

        Query receiverAccountQuery = session.createQuery("from Account where email=:email");
        receiverAccountQuery.setString("email", receiver_email);
        Account receiverAccount = (Account) receiverAccountQuery.uniqueResult();

        if (senderAccount == null) {
            throw new RuntimeException("sender email is invalid");
        }
        if (receiverAccount == null) {
            throw new RuntimeException("receiver email is invalid");
        }
        if (senderAccount.getBalance() < transfer_amount) {
            throw new RuntimeException("Insufficient balance");
        }

        senderAccount.setBalance(senderAccount.getBalance() - transfer_amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + transfer_amount);

        session.save(transaction);
        session.save(senderAccount);
        session.save(receiverAccount);
    }


    public List<Transaction> findLatestTransactions(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Transaction where sender_email=:sender_email");
        query.setParameter("sender_email", email);
        List<Transaction> transactions = (List<Transaction>) query.getResultList();
        return transactions;
    }


}
