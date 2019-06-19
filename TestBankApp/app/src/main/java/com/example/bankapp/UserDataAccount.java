package com.example.bankapp;

public class UserDataAccount {

    private String date;
    private String typeAccount;
    private double value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UserDataAccount(String date, String typeAccount, double value) {
        this.date = date;
        this.typeAccount = typeAccount;
        this.value = value;
    }
}
