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
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Account.class)
//                .addAnnotatedClass(Transaction.class)
//                .buildSessionFactory();
//
//        Session session = factory.getCurrentSession();

        Session session = entityManager.unwrap(Session.class);

        try {

//            session.beginTransaction();

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
                System.out.println("ASdasdasdasdadsadas");
                throw new RuntimeException("sender email is invalid");
            }
            if (receiverAccount == null) {
                throw new RuntimeException("receiver email is invalid");
            }
//            if (senderAccount.getBalance() < transfer_amount) {
//                throw new RuntimeException("Insufficient balance");
//            }



            session.save(transaction);


            System.out.println("@@@@@@@@@@@");




//            session.getTransaction().commit();


        } catch (Exception e) {
//            session.getTransaction().rollback();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            System.out.println(errors.toString());
        } finally {
//            session.close();
//            factory.close();

//            currentSession.close();
        }



//        Session currentSession = entityManager.unwrap(Session.class);
//        currentSession.saveOrUpdate(transaction);


        //hibenate transactions....
    }


    public List<Transaction> findLatestTransactions(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Transaction where sender_email=:sender_email");
        query.setParameter("sender_email", email);
        List<Transaction> transactions = (List<Transaction>) query.getResultList();
        return transactions;
    }


}
