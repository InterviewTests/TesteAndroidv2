package com.example.santandertestebank.model.models;

import com.google.gson.annotations.SerializedName;

public class ObjectsLogin {

    @SerializedName("userAccount")
    private UserAccountLogin userAccountLogin;

    @SerializedName("error")
    private Error errorLogin;

    public UserAccountLogin getUserAccountLogin() {
        return userAccountLogin;
    }

    public void setUserAccountLogin(UserAccountLogin userAccountLogin) {
        this.userAccountLogin = userAccountLogin;
    }

    public Error getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(Error errorLogin) {
        this.errorLogin = errorLogin;
    }
}
