package com.reagryan.online_banking.dto.response;


import java.time.LocalDateTime;
import java.util.Objects;

public class Data {
    private String firstName;
    private String lastName;
    private int acctNo;
    private String email;
    private String address;
    private LocalDateTime createdAt;
    private CardDto cardDto;

    public Data(String firstName, String lastName, int acctNo, String email, String address, LocalDateTime createdAt, CardDto cardDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.acctNo = acctNo;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.cardDto = cardDto;
    }

    public Data() {
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

    public CardDto getCardDto() {
        return cardDto;
    }

    public void setCardDto(CardDto cardDto) {
        this.cardDto = cardDto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return acctNo == data.acctNo && Objects.equals(firstName, data.firstName) && Objects.equals(lastName, data.lastName) && Objects.equals(email, data.email) && Objects.equals(address, data.address) && Objects.equals(createdAt, data.createdAt) && Objects.equals(cardDto, data.cardDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, acctNo, email, address, createdAt, cardDto);
    }

    @Override
    public String toString() {
        return "Data{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", acctNo=" + acctNo +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", cardDto=" + cardDto +
                '}';
    }
}
