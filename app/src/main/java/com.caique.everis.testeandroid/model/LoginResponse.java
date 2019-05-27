package com.caique.everis.testeandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("userAccount")
    private ClientData clientData;

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }
}
