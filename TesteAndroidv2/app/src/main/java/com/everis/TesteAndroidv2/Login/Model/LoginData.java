package com.everis.TesteAndroidv2.Login.Model;

import com.everis.TesteAndroidv2.Base.Model.Error;

public class LoginData {
    private UserAccount userAccount;
    private Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Error getError() {
        return error;
    }
}
