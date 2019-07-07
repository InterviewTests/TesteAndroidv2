package com.projeto.testedevandroidsantander.ui.loginScreen;

import com.google.gson.annotations.SerializedName;
import com.projeto.testedevandroidsantander.model.UsuarioModel;
import com.projeto.testedevandroidsantander.model.UsuarioViewModel;

public class LoginModel {
}

class LoginViewModel {
    UsuarioViewModel usuarioViewModel;
}

class LoginRequest {
    @SerializedName("user")
    private String login;
    @SerializedName("password")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginResponse {
    UsuarioModel usuarioModel;
}