package com.luizroseiro.testeandroidv2.loginScreen;

import com.luizroseiro.testeandroidv2.utils.Utils;

class LoginRequest {

    private String user;
    private String password;

    String getUser() {
        return user;
    }

    void setUser(String user) {
        this.user = user;
    }

    String getPassword() {
        return password;
    }

    boolean setPassword(String password) {
        if (!Utils.isValidPassword(password))
            return false;

        this.password = password;
        return true;
    }

}