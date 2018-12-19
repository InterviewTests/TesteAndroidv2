package com.example.jcsantos.santanderteste.Components.Objects;

import java.io.Serializable;

public class User implements Serializable{
    public static final String USER_ACCESS = "test.user_access";
    public static final String USER_PASSWORD = "test.user_password";

    int userId;
    String name;
    String bankAccount;
    String agency;
    String balance;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
