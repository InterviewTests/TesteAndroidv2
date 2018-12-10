package com.example.rossinyamaral.bank.loginScreen;

import com.example.rossinyamaral.bank.model.UserAccountModel;

public class LoginModel {
}

class LoginViewModel {
    //filter to have only the needed data
    String name;
    String bankAccount;
    String agency;
    double balance;
}

class LoginRequest {
    String user;
    String password;
}

class LoginResponse {
    UserAccountModel userAccountModel;
}
