package br.com.accenture.santander.wallacebaldenebre.model;

import java.util.ArrayList;

public class Account {
    private Error error;
    private ArrayList<StatementList> statementList;

    public Account(ArrayList<StatementList> statementList, Error error){
        this.statementList = statementList;
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ArrayList<StatementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(ArrayList<StatementList> statementList) {
        this.statementList = statementList;
    }

}
