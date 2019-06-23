package com.bilulo.androidtest04.data.model.response;

import com.bilulo.androidtest04.data.model.ErrorModel;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("userAccount")
    private UserAccountModel userAccountModel;
    @SerializedName("error")
    private ErrorModel errorModel;

    public UserAccountModel getUserAccountModel() {
        return userAccountModel;
    }

    public void setUserAccountModel(UserAccountModel userAccountModel) {
        this.userAccountModel = userAccountModel;
    }
}
