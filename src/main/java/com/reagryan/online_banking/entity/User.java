package com.reagryan.online_banking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNo;

    private String gender;
    @Column(unique = true)
    private String email;

    private LocalDateTime createdAt;
    private String acctType;
    private int acctNo;



    public User(String firstName, String lastName, String phoneNo, String gender, String email, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.email = email;
        this.createdAt = createdAt;
        this.acctType = "Savings";
        this.acctNo = 1000 + (int)(Math.random() * 89999987);
    }

    public User() {

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return acctNo == user.acctNo && Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phoneNo, user.phoneNo) && gender == user.gender && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(acctType, user.acctType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNo, gender, email, createdAt, acctType, acctNo);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", acctType='" + acctType + '\'' +
                ", acctNo=" + acctNo +
                '}';
    }
}
