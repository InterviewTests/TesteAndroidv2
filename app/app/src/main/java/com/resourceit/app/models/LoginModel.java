package com.resourceit.app.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class LoginModel {
    @Ignore
    private LoginModel userAccount;
    @PrimaryKey(autoGenerate = true)
    private int userId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "banc_account")
    private String bankAccount;
    @ColumnInfo(name = "agency")
    private String agency;
    @ColumnInfo(name = "balance")
    private String balance;

    public int getUserId() {
        return userId;
    }

    public LoginModel getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(LoginModel userAccount) {
        this.userAccount = userAccount;
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
