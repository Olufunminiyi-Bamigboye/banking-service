package com.reagryan.online_banking.dto.response;

import java.time.LocalDateTime;

public class LoginResponse {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String gender;
    private String email;
    private String token;
    private String role;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
