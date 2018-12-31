package com.home.project.testeandroidv2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class LoginResponse implements Serializable {

     /*
    Classe que recebe o objeto do serviço web após o login ser realizado
     */

    @SerializedName("userAccount")
    @Expose
    private UserAccount userAccount;
    @SerializedName("error")
    @Expose
    private Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public com.home.project.testeandroidv2.model.Error getError() {
        return error;
    }

    public void setError(com.home.project.testeandroidv2.model.Error error) {
        this.error = error;
    }
}
