package com.android.bankapp.statements;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementResponse {

    @SerializedName("statementList")
    @Expose
    private List<Statement> statementList = null;
    @SerializedName("error")
    @Expose
    private Error error;

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}

