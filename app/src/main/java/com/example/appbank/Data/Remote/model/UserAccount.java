package com.example.appbank.data.remote.model;

public class UserAccount {

    private int userId;
    private String name;
    private String bankAccount;
    private String agency;
    private float balance;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return this.bankAccount;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return this.agency;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }
}
