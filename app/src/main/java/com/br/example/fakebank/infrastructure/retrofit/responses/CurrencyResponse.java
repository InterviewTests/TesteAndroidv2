package com.br.example.fakebank.infrastructure.retrofit.responses;

import com.br.example.fakebank.infrastructure.retrofit.entities.CurrencyEntity;
import com.br.example.fakebank.infrastructure.retrofit.entities.ErrorEntity;

import java.util.List;

public class CurrencyResponse {
    private List<CurrencyEntity> statementList;
    private ErrorEntity error;

    public List<CurrencyEntity> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<CurrencyEntity> statementList) {
        this.statementList = statementList;
    }

    public ErrorEntity getError() {
        return error;
    }

    public void setError(ErrorEntity error) {
        this.error = error;
    }
}
