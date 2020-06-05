package com.bank.testeandroidv2.loginScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class ApiLoginModel{
    @SerializedName("userAccount")
    @Expose
    private UserAccount userAccount;

    @SerializedName("error")
    @Expose
    private Object error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}

class UserAccount {
    Long userId;
    String name;
    String bankAccount;
    String agency;
    Double balance;
}


