package com.example.leon.ewallet.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LatestTransactionsRequest {
    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    public LatestTransactionsRequest() {
    }

    public LatestTransactionsRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
