package com.example.TBSBank.models;

import com.example.TBSBank.enums.Type;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String nickname;
    private Integer rewards;
    private Double balance;
    private Long customerId;

    public Account() {
    }

    public Account(Long id, Type type, String nickname, Integer rewards, Double balance, Long customerId) {
        this.id = id;
        this.type = type;
        this.nickname = nickname;
        this.rewards = rewards;
        this.balance = balance;
        this.customerId = customerId;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
