package com.example.TBSBank.models;

import com.example.TBSBank.enums.WithMedium;
import com.example.TBSBank.enums.WithStatus;
import com.example.TBSBank.enums.WithType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Withdraw {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private WithStatus status;
    @Enumerated(EnumType.STRING)
    private WithMedium medium;
    @Enumerated(EnumType.STRING)
    private WithType type;
    private String transaction_date;
    private Long payerId;
    private Double amount;
    private String description;

    public Withdraw() {
    }

    public Withdraw(Long id, WithStatus status, WithMedium medium, WithType type, String transaction_date, Long payerId, Double amount, String description) {
        this.id = id;
        this.status = status;
        this.medium = medium;
        this.type = type;
        this.transaction_date = transaction_date;
        this.payerId = payerId;
        this.amount = amount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WithStatus getStatus() {
        return status;
    }

    public void setStatus(WithStatus status) {
        this.status = status;
    }

    public WithMedium getMedium() {
        return medium;
    }

    public void setMedium(WithMedium medium) {
        this.medium = medium;
    }

    public WithType getType() {
        return type;
    }

    public void setType(WithType type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
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

    @Override
    public String toString() {
        return "Withdraw{" +
                "id=" + id +
                ", status=" + status +
                ", medium=" + medium +
                ", type=" + type +
                ", transaction_date='" + transaction_date + '\'' +
                ", payerId=" + payerId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
