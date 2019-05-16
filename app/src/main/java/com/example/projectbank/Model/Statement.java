package com.example.projectbank.Model;

import java.util.List;

public class Statement {
    private List<StatementList> statementList;
    private Error error;

    public List<StatementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "statementList=" + statementList +
                ", error=" + error +
                '}';
    }
}

