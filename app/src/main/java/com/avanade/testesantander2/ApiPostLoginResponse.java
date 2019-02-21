package com.avanade.testesantander2;

import com.google.gson.annotations.Expose;

public class ApiPostLoginResponse {
    @Expose
    private UserAccount userAccount;

    @Expose
    private Erro error;

    public ApiPostLoginResponse() {
    }

    public ApiPostLoginResponse(UserAccount userAccount, Erro error) {
        this.userAccount = userAccount;
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Erro getError() {
        return error;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "\n\tuserAccount=" + userAccount.toString() +
                "\n\t, error=" + error.toString() +
                "\n}";
    }
}
