package com.example.bankapp.model.user;

public class userAccountModel {

    private userAccount userAccount;
    private com.example.bankapp.model.user.error error;

    public error getError() {
        return error;
    }

    public void setError(error error) {
        this.error = error;
    }

    public userAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(userAccount userAccount) {
        this.userAccount = userAccount;
    }



}
