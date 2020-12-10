package com.ivan.bankapp.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userAccount")
    private User userAccount;

    @SerializedName("userId")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("agency")
    private String agency;

    @SerializedName("balance")
    private Double balance;

    public User(User userAccount, Integer id, String name, String bankAccount, String agency, Double balance) {
        this.userAccount = userAccount;
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
        this.agency = agency;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }
}
