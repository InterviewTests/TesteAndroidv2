package com.bankapp.loginScreen;

import com.bankapp.ErrorResponse;

public class LoginModel {
    public String user;
    public String password;

    public LoginModel(String user, String password) {
        this.user = user;
        this.password = password;
    }
}

class LoginViewModel {
    public UserAccount userAccount;
    public ErrorResponse error;
    public LoginModel loginModel;
    public boolean wrongUser;
    public boolean wrongPassword;
}

class LoginRequest {
    public LoginModel loginModel;
}

