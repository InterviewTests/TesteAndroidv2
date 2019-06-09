package br.com.douglas.fukuhara.bank.network.vo;

import com.google.gson.annotations.SerializedName;

public class UserError {
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}