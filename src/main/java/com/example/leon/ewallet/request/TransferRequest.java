package com.example.leon.ewallet.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransferRequest {
    @NotBlank(message = "sender email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "transferee email is mandatory")
    @Email
    private String transferee;

    private int amount;

    public TransferRequest() {
    }

    public TransferRequest(String email, String transferee, int amount) {
        this.email = email;
        this.transferee = transferee;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransferee() {
        return transferee;
    }

    public void setTransferee(String transferee) {
        this.transferee = transferee;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
