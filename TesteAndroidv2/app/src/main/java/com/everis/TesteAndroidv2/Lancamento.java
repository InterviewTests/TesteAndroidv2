package com.everis.TesteAndroidv2;

import java.util.ArrayList;
import java.util.HashMap;

public class Lancamento {
    private ArrayList<Statements> statementList;
    private Error error;

    public ArrayList<Statements> getStatementList() {
        return statementList;
    }

    public void setStatementList(ArrayList<Statements> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}