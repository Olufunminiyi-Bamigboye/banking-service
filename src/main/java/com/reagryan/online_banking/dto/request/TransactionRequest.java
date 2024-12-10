package com.reagryan.online_banking.dto.request;

import com.reagryan.online_banking.entity.User;

import java.time.LocalDateTime;

public class TransactionRequest {
    private double amount;
    private double balance;
    private String transactionType;
    private String transactionRef;
    private LocalDateTime transactionDate;
    private User user;

    public TransactionRequest(double amount) {
        this.amount = amount;
    }

    public TransactionRequest(){

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
