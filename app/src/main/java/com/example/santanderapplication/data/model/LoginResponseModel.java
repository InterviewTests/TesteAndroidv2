package com.example.santanderapplication.data.model;

import com.example.santanderapplication.data.UserAccount;

public class LoginResponseModel {

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
