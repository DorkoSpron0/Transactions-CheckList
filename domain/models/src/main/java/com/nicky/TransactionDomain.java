package com.nicky;

import java.time.LocalDateTime;

public class TransactionDomain {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private Long amount;
    private boolean isCompleted;

    private UserDomain user;

    public TransactionDomain() {
    }

    public TransactionDomain(Long id, String title, String description, LocalDateTime dateTime, Long amount, boolean isCompleted, UserDomain user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.amount = amount;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }
}
