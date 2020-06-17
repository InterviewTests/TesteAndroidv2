package com.dev.appbank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StatementList implements Serializable {

    private static final long serialVersionUID = 1L;
    @SerializedName("statementList")
    private List<Statement> statementList;

    public StatementList() {

    }

    public StatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

}
