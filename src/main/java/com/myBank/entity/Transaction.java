package com.myBank.entity;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId; // Unique identifier for each transaction

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // Reference to the account involved in the transaction

    private String transactionType; // Type of transaction (Debit/Credit)

    @Column(precision = 15, scale = 2)
    private BigDecimal amount; // Amount involved in the transaction

    @Column(precision = 15, scale = 2)
    private BigDecimal transactionFee; // Fee charged for the transaction (if applicable)

    private LocalDateTime timestamp; // Date and time when the transaction occurred

    private String description; // Description of the transaction

    // Getters and Setters


    public Transaction() {
    }

    public Transaction(Long transactionId, Account account, String transactionType, BigDecimal amount, BigDecimal transactionFee, LocalDateTime timestamp, String description) {
        this.transactionId = transactionId;
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionFee = transactionFee;
        this.timestamp = timestamp;
        this.description = description;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", account=" + account +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionFee=" + transactionFee +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}
