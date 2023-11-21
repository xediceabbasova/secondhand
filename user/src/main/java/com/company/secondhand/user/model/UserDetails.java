package com.company.secondhand.user.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String country;
    private String postCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userDetails")
    private Set<PhoneNumber> phoneNumbers;

    public UserDetails(Long id, Set<PhoneNumber> phoneNumbers, String address, String city, String country, String postCode, Users users) {
        this.id = id;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.users = users;
    }

    public UserDetails(Set<PhoneNumber> phoneNumbers, String address, String city, String country, String postCode, Users users) {
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.users = users;
    }

    public UserDetails() {

    }

    public Long getId() {
        return id;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public Users getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(postCode, that.postCode) && Objects.equals(users, that.users) && Objects.equals(phoneNumbers, that.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, country, postCode, users, phoneNumbers);
    }
}
