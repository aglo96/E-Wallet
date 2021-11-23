package com.example.leon.ewallet.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="type")
    private String type;


    @Column(name="sender_email")
    @NotBlank(message = "sender email is mandatory")
    @Email
    private String sender_email;

    @Column(name="receiver_email")
    @NotBlank(message = "receiver email is mandatory")
    @Email
    private String receiver_email;

    @Column(name="amount")
    private int amount;

    @Column(name="datetime")
    private Date datetime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Transaction() {
    }

    public Transaction(int id, String type, String sender_email, String receiver_email, int amount, Date datetime) {
        this.id = id;
        this.type = type;
        this.sender_email = sender_email;
        this.receiver_email = receiver_email;
        this.amount = amount;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender_email() {
        return sender_email;
    }

    public void setSender_email(String sender_email) {
        this.sender_email = sender_email;
    }

    public String getReceiver_email() {
        return receiver_email;
    }

    public void setReceiver_email(String receiver_email) {
        this.receiver_email = receiver_email;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
