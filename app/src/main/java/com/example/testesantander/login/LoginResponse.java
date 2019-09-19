package com.example.testesantander.login;

import com.google.gson.annotations.SerializedName;

import com.example.testesantander.model.Error;
import com.example.testesantander.domain.User;

public class LoginResponse {
    private Error error;

    @SerializedName("userAccount")
    private User user;

    public LoginResponse(Error error, User user) {
        this.error = error;
        this.user = user;
    }

    public Error getError() {
        return error;
    }

    public User getUser() {
        return user;
    }
}
