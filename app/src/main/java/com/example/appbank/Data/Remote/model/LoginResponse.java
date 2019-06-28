package com.example.appbank.data.remote.model;

public class LoginResponse {

    private Error error;
    private UserAccount userAccount;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
