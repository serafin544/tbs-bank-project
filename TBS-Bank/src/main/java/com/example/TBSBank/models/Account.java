package com.example.TBSBank.models;

import com.example.TBSBank.enums.Type;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String nickname;
    private Integer rewards;
    private Double balance;
    private Long customerId;

    public Account() {
    }

    public Account(Long accountId, String nickname, Integer rewards, Double balance, Long customerId)
    {
        this.balance = balance;
        this.customerId = customerId;
        this.nickname = nickname;
        this.rewards = rewards;
        this.accountId = accountId;
    }
    public enum type {

        Savings,Checking,Credit
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getRewards() {
        return rewards;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomer(Long customerId) {
        this.customerId = customerId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setId(Long accountId) {
        this.accountId = accountId;

    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + accountId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", rewards=" + rewards +
                ", balance=" + balance +
                ", customer=" + customerId +
                '}';
    }
}
