package com.reagryan.online_banking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reagryan.online_banking.entity.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class CardDto {
    private String cardNumber;
    private int cvv;
    private String cardType;
    private Date expiresAt;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private User user;

    public CardDto(String cardNumber, int cvv, String cardType, Date expiresAt, User user) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardType = cardType;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public CardDto() {
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

    public User getUser() {
        return user;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardDto cardDto = (CardDto) o;
        return cvv == cardDto.cvv && Objects.equals(cardNumber, cardDto.cardNumber) && Objects.equals(cardType, cardDto.cardType) && Objects.equals(expiresAt, cardDto.expiresAt)  && Objects.equals(user, cardDto.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cvv, cardType, expiresAt, user);
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv=" + cvv +
                ", cardType='" + cardType + '\'' +
                ", expiresAt=" + expiresAt +
                ", user=" + user +
                '}';
    }
}

