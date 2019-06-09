package br.com.douglas.fukuhara.bank.network.vo;

import com.google.gson.annotations.SerializedName;

public class LoginVo  {

    @SerializedName("userAccount")
    UserAccount userAccount;

    @SerializedName("error")
    UserError error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public UserError getUserError() {
        return  error;
    }
}
