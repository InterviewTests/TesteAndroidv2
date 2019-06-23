package com.bilulo.androidtest04.data.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class UserAccountModel {
    @SerializedName("userId")
    public Integer userId;
    @SerializedName("name")
    public String name;
    @SerializedName("bankAccount")
    public String bankAccount;
    @SerializedName("agency")
    public String agency;
    @SerializedName("balance")
    public BigDecimal balance;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
