package com.avanade.testesantander2;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserAccount implements Serializable {

    @Expose
    private int userId;
    @Expose
    private String name;
    @Expose
    private String bankAccount;
    @Expose
    private String agency;
    @Expose
    private double balance;

    //TODO - campo para TOKEN da WebAPI

    public UserAccount() {
    }

    public UserAccount(int userId, String name, String bankAccount, String agency, double balance) {
        this.userId = userId;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getBankAccount() {
        return bankAccount;
    }
    public String getAgency() {
        return agency;
    }
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "agency='" + agency + '\'' +
                ", balance=" + balance +
                ", bankAccount='" + bankAccount + '\'' +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}

/*

 {
     "userAccount": {
         "userId": 1,
         "name": "Jose da Silva Teste",
         "bankAccount": "2050",
         "agency": "012314564",
         "balance": 3.3445
     },
     "error": {}
 }

 */