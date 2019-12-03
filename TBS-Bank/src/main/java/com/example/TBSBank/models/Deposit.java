package com.example.TBSBank.models;

import com.example.TBSBank.enums.DepoMedium;
import com.example.TBSBank.enums.DepoStatus;
import com.example.TBSBank.enums.DepoType;

import javax.persistence.*;

@Entity
public class Deposit {

    @Id
    @GeneratedValue
    @Column(name="DEPOSIT_ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    private DepoStatus status;
    @Enumerated(EnumType.STRING)
    private DepoMedium medium;
    @Enumerated(EnumType.STRING)
    private DepoType type;
    private String transaction_date;
    private Long payeeId;
    private Double amount;
    private String description;
    private Long accountId;

    public Deposit() {
    }

    public Deposit(Long id, DepoStatus status, DepoMedium medium, DepoType type, String transaction_date, Long payeeId, Double amount, String description, Long accountId) {
        this.id = id;
        this.status = status;
        this.medium = medium;
        this.type = type;
        this.transaction_date = transaction_date;
        this.payeeId = payeeId;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DepoStatus getStatus() {
        return status;
    }

    public void setStatus(DepoStatus status) {
        this.status = status;
    }

    public DepoMedium getMedium() {
        return medium;
    }

    public void setMedium(DepoMedium medium) {
        this.medium = medium;
    }

    public DepoType getType() {
        return type;
    }

    public void setType(DepoType type) {
        this.type = type;
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
                "id=" + id +
                ", status=" + status +
                ", medium=" + medium +
                ", type=" + type +
                ", transaction_date='" + transaction_date + '\'' +
                ", payeeId=" + payeeId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}

