package com.android.bankapp.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("userAccount")
    @Expose
    private UserAccount userAccount;
    @SerializedName("error")
    @Expose
    private Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userAccount=" + userAccount +
                ", error=" + error +
                '}';
    }
}


