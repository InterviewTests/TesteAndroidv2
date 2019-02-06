package com.luizroseiro.testeandroidv2.models;

import com.luizroseiro.testeandroidv2.utils.Utils;

import java.io.Serializable;

public class UserModel implements Serializable {

    private int userId;
    private String name;
    private String agency;
    private String bankAccount;
    private float balance;

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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getFormattedAgency() {
        return Utils.formatAgency(this.agency);
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}