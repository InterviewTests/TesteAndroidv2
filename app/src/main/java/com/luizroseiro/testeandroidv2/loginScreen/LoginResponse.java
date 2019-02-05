package com.luizroseiro.testeandroidv2.loginScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("userAccount")
    @Expose
    private UserAccount userAccount;

    @SerializedName("Error")
    @Expose
    private Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Error getError() {
        return error;
    }

}

class UserAccount {

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

class Error {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}