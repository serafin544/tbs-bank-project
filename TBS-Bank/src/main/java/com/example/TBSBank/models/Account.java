package com.example.TBSBank.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Account {


    private Long accountId;
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

    public void setAccountId(Long accountId) {
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
