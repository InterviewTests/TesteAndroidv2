package com.avanade.testesantander2.loginScreen;

import com.avanade.testesantander2.ApiPostLoginResponse;
import com.avanade.testesantander2.Erro;
import com.avanade.testesantander2.UserAccount;
import com.google.gson.annotations.Expose;

public class LoginModel {

}

class LoginViewModel{
    // Response da API
    // LoginResponse[UserAccount userAccount, Erro error]
    UserAccount userAccount;
}

class LoginRequest{
    String user;
    String password;
}

class LoginResponse{
    ApiPostLoginResponse apiPostLoginResponse;

}
