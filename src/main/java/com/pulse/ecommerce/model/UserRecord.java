package com.pulse.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "users")  //User is a reserved keyword in mySql
public class UserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fName;
    private String lName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String password;
    private String role;
    private LocalDate registrationDate;
    private LocalDate last_loginDate;


    public UserRecord(){

    }

    public UserRecord(String fName, String lName, String email, String phoneNumber, Long userId, String role, LocalDate registrationDate, LocalDate last_loginDate) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.role = role;
        this.registrationDate = registrationDate;
        this.last_loginDate = last_loginDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getLast_loginDate() {
        return last_loginDate;
    }

    public void setLast_loginDate(LocalDate last_loginDate) {
        this.last_loginDate = last_loginDate;
    }

}
