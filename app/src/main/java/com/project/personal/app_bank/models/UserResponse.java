package com.project.personal.app_bank.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("userAccount")
    @Expose
    User userAccount;

    public User getUserAccount() {
        return userAccount;
    }
}
