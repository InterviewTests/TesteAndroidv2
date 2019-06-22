package com.example.projectsantander.services;

import com.example.projectsantander.transacao.Transacao;

import java.util.List;

public class TransacaoResponse {

    private List<Transacao> statementList;
    private Error error;

    public List<Transacao> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Transacao> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
