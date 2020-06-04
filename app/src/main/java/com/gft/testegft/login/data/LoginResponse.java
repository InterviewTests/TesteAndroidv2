package com.gft.testegft.login.data;

public class LoginResponse {
    private UserAccount userAccount;
    private Error error;

    public LoginResponse(UserAccount userAccount, Error error) {
        this.userAccount = userAccount;
        this.error = error;
    }

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
