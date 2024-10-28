package com.myBank.entity;



import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    private String message;
    private LocalDateTime timestamp;

    // Getters and Setters


    public Notification() {
    }

    public Notification(Long notificationId, Account account, String message, LocalDateTime timestamp) {
        this.notificationId = notificationId;
        this.account = account;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", account=" + account +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
