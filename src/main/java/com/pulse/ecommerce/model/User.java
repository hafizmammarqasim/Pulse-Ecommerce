package com.pulse.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fName;
    private String lName;
    private String email;
    private int phoneNumber;
    private String role;
    private LocalDate registrationDate;
    private LocalDate last_loginDate;

    @OneToMany(mappedBy = "userId")
    private List<Address> addressList;

    public User(){

    }

    public User(String fName, String lName, String email, int phoneNumber, Long userId, String role, LocalDate registrationDate, LocalDate last_loginDate, List<Address> addressList) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.role = role;
        this.registrationDate = registrationDate;
        this.last_loginDate = last_loginDate;
        this.addressList = addressList;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
