package com.testeandroidv2.repository.response;

import java.util.List;

public class StatementResponse {

    private List<Statement> statementList;
    private Error error;

    public StatementResponse(List<Statement> statementList, Error error){
        this.statementList = statementList;
        this.error = error;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public Error getError() {
        return error;
    }

}
