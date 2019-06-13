package com.accenture.project.apptesteandroid.model;

import com.accenture.project.apptesteandroid.model.UserAccount;

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
