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
    private Customer customer;
    private String TODO;
// id ,type, nickname, rewards, balance, customerId;

    public Account(Long id,String nickname,Integer rewards,Double balance, Customer customer)
    {
        this.balance = balance;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
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
                ", customer=" + customer +
                ", TODO='" + TODO + '\'' +
                '}';
    }
}
