package com.ivan.bankapp.model;

import com.google.gson.annotations.SerializedName;

public class StatementList {

    @SerializedName("statementList")
    private Statements[] statementList;

    public StatementList(Statements[] statementList) {
        this.statementList = statementList;
    }

    public Statements[] getStatementList() {
        return statementList;
    }

    public void setStatementList(Statements[] statementList) {
        this.statementList = statementList;
    }
}
