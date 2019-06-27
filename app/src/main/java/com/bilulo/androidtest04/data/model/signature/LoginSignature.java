package com.bilulo.androidtest04.data.model.signature;

import com.google.gson.annotations.SerializedName;

public class LoginSignature {
    @SerializedName("user")
    private String user;
    @SerializedName("password")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
