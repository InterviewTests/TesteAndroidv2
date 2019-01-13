package com.resourceit.app.models;

import java.util.List;

public class LoginModel {
    private List<LoginModel> userAccount;
    private double userId;
    private String name;
    private String bankAccount;

    public List<LoginModel> getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(List<LoginModel> userAccount) {
        this.userAccount = userAccount;
    }

    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private String agency;
    private double balance;


}
