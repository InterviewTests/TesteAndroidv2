package com.luizroseiro.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAccount {

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;

    @SerializedName("agency")
    @Expose
    private String agency;

    @SerializedName("balance")
    @Expose
    private double balance;

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

}
