package com.luizroseiro.testeandroidv2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Statements {

    @SerializedName("statementList")
    @Expose
    private List<StatementData> statementList;

    @SerializedName("error")
    @Expose
    private ErrorResponse error;

    public List<StatementData> getStatementList() {
        return statementList;
    }

}
