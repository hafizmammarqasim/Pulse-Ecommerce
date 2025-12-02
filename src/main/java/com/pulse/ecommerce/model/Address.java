package com.pulse.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false)
    private String addressLine1;  //HouseNumber or StreetNumber
    private String landMark ;   //Landmark
    @Column(nullable = false)
    private String area;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private int postalCode;

    private String country = "Pakistan";

    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Address() {
    }

    public Address(String addressLine1, Long addressId, String landMark, String area, String city, int postalCode, String country, Boolean isDefault, User user) {
        this.addressLine1 = addressLine1;
        this.addressId = addressId;
        this.landMark = landMark;
        this.area = area;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.isDefault = isDefault;
        this.user = user;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
