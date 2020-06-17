package com.dev.appbank.model;

import com.dev.appbank.util.Common;

public class Login {

    private String user;
    private String password;

    public Login() {

    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

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
