package com.bankapp.loginScreen;

import com.bankapp.ErrorResponse;

public class LoginResponse {
    public UserAccount userAccount;
    public ErrorResponse error;
    public LoginModel loginModel;
    public boolean wrongUser = false;
    public boolean wrongPassword = false;
}
