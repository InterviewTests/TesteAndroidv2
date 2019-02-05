package com.luizroseiro.testeandroidv2.loginScreen;

import com.luizroseiro.testeandroidv2.utils.Utils;

public class LoginRequest {

    private String user;
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

    public boolean setPassword(String password) {
        if (!Utils.isValidPassword(password))
            return false;

        this.password = password;
        return true;
    }

}
