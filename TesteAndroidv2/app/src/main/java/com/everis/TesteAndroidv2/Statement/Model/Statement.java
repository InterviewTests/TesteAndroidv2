package com.everis.TesteAndroidv2.Statement.Model;

import com.everis.TesteAndroidv2.Base.Model.Error;

import java.util.ArrayList;

public class Statement {
    private ArrayList<TransactionInfo> statementList;
    private Error error;

    public ArrayList<TransactionInfo> getStatementList() {
        return statementList;
    }

    public Error getError() {
        return error;
    }
}