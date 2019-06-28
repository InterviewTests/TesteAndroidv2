package com.example.appbank.data.remote.model;

import java.util.List;

public class StatementResponse {

    private List<Statement> statementList;
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
