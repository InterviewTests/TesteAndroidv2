package com.example.bankapp.model.dashboard;

import com.example.bankapp.model.user.Error;

import java.util.List;

public class StatementListModel {
    private List<StatementList> statementList;
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<StatementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        this.statementList = statementList;


    }




}
