package com.reagryan.online_banking.dto.request;


import java.time.LocalDateTime;
import java.util.Objects;


public class CardRequest {
    private String firstName;
    private String lastName;
    private int acctNo;
    private String email;
    private String address;
    private LocalDateTime createdAt;


    public CardRequest(String firstName, String lastName, int acctNo, String email, String address, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.acctNo = acctNo;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
    }

    public CardRequest() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(int acctNo) {
        this.acctNo = acctNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardRequest that = (CardRequest) o;
        return acctNo == that.acctNo && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, acctNo, email, address, createdAt);
    }

    @Override
    public String toString() {
        return "BankCardRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", acctNo=" + acctNo +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
