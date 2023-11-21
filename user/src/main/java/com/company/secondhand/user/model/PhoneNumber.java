package com.company.secondhand.user.model;

import jakarta.persistence.*;

@Entity
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryCode;
    private String phoneNumber;
    private PhoneNumberType phoneNumberType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id", nullable = false)
    private UserDetails userDetails;


    public PhoneNumber(String countryCode, String phoneNumber, PhoneNumberType phoneNumberType) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
    }

    public PhoneNumber(Long id, String countryCode, String phoneNumber, PhoneNumberType phoneNumberType, UserDetails userDetails) {
        this.id = id;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
        this.userDetails = userDetails;
    }

    public PhoneNumber() {

    }

    public Long getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
