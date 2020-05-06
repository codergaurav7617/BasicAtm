package com.ril.jpm.test.security.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int accountId;
    @Getter @Setter
    private String username;
    @Getter
    private double amount=0.0;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public Account(){};

    public Account(String username){
        this.username=username;
    }

    public void setAmount(double amount) {
        this.amount += amount;
    }

}