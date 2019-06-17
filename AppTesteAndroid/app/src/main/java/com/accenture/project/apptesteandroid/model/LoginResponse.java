package com.accenture.project.apptesteandroid.model;

/**
  Recebe os dados convertidos por Jackson configurados no Retrofit, após a chamada da API
 */
public class LoginResponse {

    private UserAccount userAccount;
    private Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
