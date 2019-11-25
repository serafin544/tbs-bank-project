package com.example.TBSBank.models;

import com.example.TBSBank.enums.DepoStatus;
import com.example.TBSBank.enums.DepoType;
import com.example.TBSBank.enums.Medium;

import javax.persistence.*;

@Entity
public class Deposit {

    @Enumerated(EnumType.STRING)
    private DepoType type;

    @Enumerated(EnumType.STRING)
    private Medium medium;

    @Enumerated(EnumType.STRING)
    private DepoStatus status;

    @Id
    @Column(name="DEPOSIT_ID")
    private Long id;
    private String transaction_date;
    private Long payeeId;
    private Double amount;
    private String description;
    private Long accountId;

    public Deposit() {
    }

    public Deposit(DepoType type, Medium medium, DepoStatus status, Long id, String transaction_date, Long payeeId, Double amount, String description, Long accountId) {
        this.type = type;
        this.medium = medium;
        this.status = status;
        this.id = id;
        this.transaction_date = transaction_date;
        this.payeeId = payeeId;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }

    public DepoType getType() {
        return type;
    }

    public void setType(DepoType type) {
        this.type = type;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public DepoStatus getStatus() {
        return status;
    }

    public void setStatus(DepoStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "type=" + type +
                ", medium=" + medium +
                ", status=" + status +
                ", id=" + id +
                ", transaction_date='" + transaction_date + '\'' +
                ", payeeId=" + payeeId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}

