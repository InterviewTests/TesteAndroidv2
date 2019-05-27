package com.caique.everis.testeandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountInfoResponse {

    @SerializedName("statementList")
    private List<AccountInfo> accountInfos;


    public List<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(List<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
    }
}
