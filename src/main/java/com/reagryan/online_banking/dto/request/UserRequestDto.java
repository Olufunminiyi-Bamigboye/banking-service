package com.reagryan.online_banking.dto.request;

import com.reagryan.online_banking.entity.Role;

import java.time.LocalDateTime;

public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String gender;
    private String email;
    private Role role;
    private String password;
    private String address;
    private LocalDateTime createdAt;

    public UserRequestDto(String firstName, String lastName, String phoneNo, String gender, String email, String password, String address, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public UserRequestDto(String firstName, String lastName, String phoneNo, String gender, String email, String address, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
    }

    public UserRequestDto(){

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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
