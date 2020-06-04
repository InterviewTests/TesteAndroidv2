package com.gft.testegft.statements.data;

import java.util.List;

public class StatementResponse {
    private List<Statement> statementList;

    StatementResponse(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }
}
