package com.example.TBSBank.models;

import com.example.TBSBank.enums.BillStatus;

import javax.persistence.*;

@Entity
public class Bill {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    private String payee;
    private String nickname;
    private String creationDate;
    private String paymentDate;
    private Integer recurringDate;
    private String upcomingPaymentDate;
    private Double paymentAmount;
    private String accountId;

    public Bill() {
    }

    public Bill(Long id, String payee, String nickname, String creationDate, String paymentDate, Integer recurringDate, String upcomingPaymentDate, Double paymentAmount, String accountId, BillStatus status) {
        this.id = id;
        this.payee = payee;
        this.nickname = nickname;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.recurringDate = recurringDate;
        this.upcomingPaymentDate = upcomingPaymentDate;
        this.paymentAmount = paymentAmount;
        this.accountId = accountId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getRecurringDate() {
        return recurringDate;
    }

    public void setRecurringDate(Integer recurringDate) {
        this.recurringDate = recurringDate;
    }

    public String getUpcomingPaymentDate() {
        return upcomingPaymentDate;
    }

    public void setUpcomingPaymentDate(String upcomingPaymentDate) {
        this.upcomingPaymentDate = upcomingPaymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", payee='" + payee + '\'' +
                ", nickname='" + nickname + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", recurringDate=" + recurringDate +
                ", upcomingPaymentDate='" + upcomingPaymentDate + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", accountId='" + accountId + '\'' +
                ", status=" + status +
                '}';
    }
}
