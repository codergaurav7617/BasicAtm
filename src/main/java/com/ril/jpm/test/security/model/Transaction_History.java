package com.ril.jpm.test.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Transaction_History {
    @Id
    @GeneratedValue
    private int txn_id;

    private String username;
    private double amount;
    private Date time_stamp;
    private String comment;
    private String txnType;

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public int getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(int txn_id) {
        this.txn_id = txn_id;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTxnType() {
        return txnType;
    }

    public Transaction_History(){}

    public Transaction_History(String username,double amount,Date time_stamp,String comment,String txn_type){
        this.username=username;
        this.amount=amount;
        this.time_stamp=time_stamp;
        this.txnType=txn_type;
        this.comment=comment;
    }

    @Override
    public String toString() {
        return "Transaction_History{" +
                "txn_id=" + txn_id +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", time_stamp=" + time_stamp +
                ", comment='" + comment + '\'' +
                ", txn_type='" + txnType + '\'' +
                '}';
    }
}