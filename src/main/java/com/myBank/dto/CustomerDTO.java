package com.myBank.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data


public class CustomerDTO {
    private Long customerId;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private LocalDate dob;
    private LocalDateTime registrationDate;

    public CustomerDTO(Long customerId, String name, String address, String contactNumber, String email, LocalDate dob, LocalDateTime registrationDate) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.dob = dob;
        this.registrationDate = registrationDate;
    }

    public CustomerDTO() {
    }

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

    @Override
    public String toString() {
        return "CustomerDTO{" +
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
