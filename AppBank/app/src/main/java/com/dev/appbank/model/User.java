package com.dev.appbank.model;

public class User {

    private UserAccount userAccount;

    public User() {

    }

    public User(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
