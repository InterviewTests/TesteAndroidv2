package com.example.projectsantander.services;

import com.example.projectsantander.login.Login;

import java.io.Serializable;

public class LoginResponse implements Serializable{

    private Login userAccount;
    private Error error;

    public Login getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Login userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
