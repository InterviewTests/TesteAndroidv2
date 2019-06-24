package com.example.bankapp.data.remote.model.user;

import java.io.Serializable;

public class UserAccountModel implements Serializable {

    private UserAccount userAccount;
    private Error error;

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
