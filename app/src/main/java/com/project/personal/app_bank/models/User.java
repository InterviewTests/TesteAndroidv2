package com.project.personal.app_bank.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;

    @SerializedName("agency")
    @Expose
    private String agency;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("balance")
    @Expose
    private float balance;


    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public int getUserId() {
        return userId;
    }

    public float getBalance() {
        return balance;
    }

    public String getAgency() {
        return agency;
    }

    public void setName(String name) {
        this.name = name;
    }
}
