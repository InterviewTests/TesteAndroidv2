package com.luizroseiro.testeandroidv2.mainScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementsResponse {

    @SerializedName("statementList")
    @Expose
    private List<Statement> statements;

    @SerializedName("error")
    @Expose
    private Error error;

    List<Statement> getStatements() {
        return statements;
    }

    public Error getError() {
        return error;
    }

}

class Statement {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("value")
    @Expose
    private float value;

    String getTitle() {
        return title;
    }

    String getDesc() {
        return desc;
    }

    String getDate() {
        return date;
    }

    float getValue() {
        return value;
    }

}

class Error {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}