package com.reagryan.online_banking.dto.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TransactionResponse {
    private double balance;
    @NotBlank
    private double amount;
    private String transactionType;
    @Column(name = "Ref_Number")
    private String transactionRef;
    private LocalDateTime transactionDate;

    public TransactionResponse(double balance, double amount, String transactionType, String transactionRef, LocalDateTime transactionDate) {
        this.balance = balance;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionRef = transactionRef;
        this.transactionDate = transactionDate;
    }

    public double getBalance() {
         return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
