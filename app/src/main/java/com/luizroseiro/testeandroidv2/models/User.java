package com.luizroseiro.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userAccount")
    @Expose
    private UserAccount userAccount;

    @SerializedName("error")
    @Expose
    private ErrorResponse error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public ErrorResponse getError() {
        return error;
    }

}
