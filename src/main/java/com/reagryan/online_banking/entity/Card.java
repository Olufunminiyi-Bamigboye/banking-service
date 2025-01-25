package com.reagryan.online_banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.aspectj.apache.bcel.classfile.Unknown;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String cardNumber;
    private int cvv;
    private String cardType;
    private Date expiresAt;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Card(String cardNumber, int cvv, String cardType, Date expiresAt, LocalDateTime createdAt, User user) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardType = cardType;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Card() {
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cvv == card.cvv && Objects.equals(id, card.id) && Objects.equals(cardNumber, card.cardNumber) && Objects.equals(cardType, card.cardType) && Objects.equals(expiresAt, card.expiresAt) && Objects.equals(user, card.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, cvv, cardType, expiresAt, user);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv=" + cvv +
                ", cardType='" + cardType + '\'' +
                ", expiresAt=" + expiresAt +
                ", user=" + user +
                '}';
    }
}