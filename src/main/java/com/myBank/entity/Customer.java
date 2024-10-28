package com.myBank.entity;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId; // Unique identifier


    private String name; // Full name of the customer
    private String address; // Residential address
    private String contactNumber; // Customer's contact phone number
    private String email; // Customer's email address
    private LocalDate dob; // Date of birth
    private LocalDateTime registrationDate; // Registration date and time



    // Relationship
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts; // List of accounts linked to the customer


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Customer(Long customerId, String name, String address, String contactNumber, String email, LocalDate dob, LocalDateTime registrationDate) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.dob = dob;
        this.registrationDate = registrationDate;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", registrationDate=" + registrationDate +
                '}';
    }

}
