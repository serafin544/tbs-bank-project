package com.example.TBSBank.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private Integer rewards;
    private Double balance;
    private Long customerId;

    public Account() {
    }

    public Account(Long id, String nickname, Integer rewards, Double balance, Long customerId)
    {
        this.balance = balance;
        this.customerId = customerId;
        this.nickname = nickname;
        this.rewards = rewards;
        this.id = id;
    }
    public enum type {

        Savings,Checking,Credit
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;

    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", rewards=" + rewards +
                ", balance=" + balance +
                ", customer=" + customerId +
                '}';
    }
}
