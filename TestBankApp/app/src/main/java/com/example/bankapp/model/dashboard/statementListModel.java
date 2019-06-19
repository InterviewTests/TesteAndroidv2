package com.example.bankapp.model.dashboard;

import com.example.bankapp.model.user.error;

import java.util.List;

public class statementListModel {
    private List<statementList> statementList;
    private error error;

    public error getError() {
        return error;
    }

    public void setError(error error) {
        this.error = error;
    }

    public List<statementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<statementList> statementList) {
        this.statementList = statementList;


    }




}
