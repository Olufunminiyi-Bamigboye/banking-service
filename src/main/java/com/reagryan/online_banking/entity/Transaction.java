package com.reagryan.online_banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;
    private double amount;
    private String transactionType;
    private String transactionRef;
    private LocalDateTime transactionDate;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private User user;

    public Transaction(double balance, double amount, String transactionType, LocalDateTime transactionDate, User user) {
        this.balance = balance;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionRef = "F" + 1000 + (int)(Math.random() * 69999);
        this.transactionDate = transactionDate;
        this.user = user;
    }

    public Transaction(){

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void deposit(double amount){
        balance+=amount;
        this.transactionType = "deposit";
    }

    public double getBalance(){
        return balance;
    }

    public boolean withdraw(double amount){
        if(amount < balance){
            balance-=amount;
            this.transactionType = "withdrawal";
            return true;
        }
        return false;
    }
}
