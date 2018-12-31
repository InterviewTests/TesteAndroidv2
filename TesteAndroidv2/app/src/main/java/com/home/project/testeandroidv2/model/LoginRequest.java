package com.home.project.testeandroidv2.model;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    /*
    Classe que gera o objeto enviado no método post do serviço web para realizar um login
     */

    private String user;
    private String password;

    public LoginRequest(String user, String password) {
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
