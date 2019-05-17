package com.testeandroidv2.repository.response;

import com.google.gson.annotations.SerializedName;

public class UserAccount {

    @SerializedName("userId")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("agency")
    private String agency;

    @SerializedName("balance")
    private String balance;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return "R$ " + balance;
    }

    public String getFullBankData(){
        return bankAccount + " / " + agency;
    }
}
