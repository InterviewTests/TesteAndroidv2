package com.br.example.fakebank.infrastructure.retrofit.responses;

import com.br.example.fakebank.infrastructure.retrofit.entities.StatementEntity;
import com.br.example.fakebank.infrastructure.retrofit.entities.ErrorEntity;

import java.util.List;

public class StatementResponse {
    private List<StatementEntity> statementList;
    private ErrorEntity error;

    public List<StatementEntity> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementEntity> statementList) {
        this.statementList = statementList;
    }

    public ErrorEntity getError() {
        return error;
    }

    public void setError(ErrorEntity error) {
        this.error = error;
    }
}
