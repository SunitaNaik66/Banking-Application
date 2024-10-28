package com.myBank.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId; // Unique identifier for each account (Primary Key)

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Reference to the customer (Foreign Key)

    private String accountType; // Type of account (Current/Savings)

    @Column(precision = 15, scale = 2)
    private BigDecimal balance; // Current balance in the account

    private LocalDateTime creationDate; // Date and time when the account was created

    private String status;// Status of the account (Active/Inactive)



    // Method to calculate transaction fee for Current Account
    public BigDecimal calculateTransactionFee(BigDecimal amount) {
        return amount.multiply(new BigDecimal("0.0005")); // 0.05% fee
    }

    // Method to calculate interest for Savings Account
    public BigDecimal calculateInterest() {
        return balance.multiply(new BigDecimal("0.005")); // 0.5% interest
    }

    public Account(Long accountId, Customer customer, String accountType, BigDecimal balance, LocalDateTime creationDate, String status) {
        this.accountId = accountId;
        this.customer = customer;
        this.accountType = accountType;
        this.balance = balance;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customer=" + customer +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", status='" + status + '\'' +
                '}';
    }
}
