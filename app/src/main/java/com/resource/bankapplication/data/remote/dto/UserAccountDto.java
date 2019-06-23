package com.resource.bankapplication.data.remote.dto;

public class UserAccountDto {
    private LoginDto userAccount;
    private Error error;

    public LoginDto getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(LoginDto userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
