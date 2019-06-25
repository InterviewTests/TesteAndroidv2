package com.example.santandertestebank.model.models;

public class ObjectsLogin {

    private UserAccountLogin userAccountLogin;

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
