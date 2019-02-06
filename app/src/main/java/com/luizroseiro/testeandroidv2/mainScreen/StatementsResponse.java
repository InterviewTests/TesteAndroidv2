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

    public List<Statement> getStatements() {
        return statements;
    }

    public Error getError() {
        return error;
    }

}

class Statement {

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