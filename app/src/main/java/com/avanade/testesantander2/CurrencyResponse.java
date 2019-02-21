package com.avanade.testesantander2;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CurrencyResponse {

    @Expose
    private ArrayList<Statement> statementList;
    @Expose
    private Erro error;

    public CurrencyResponse() {
    }

    public CurrencyResponse(ArrayList<Statement> statementList, Erro error) {
        this.statementList = statementList;
        this.error = error;
    }

    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    public Erro getError() {
        return error;
    }

    @Override
    public String toString() {


        StringBuilder returnStringBuilder = new StringBuilder("");
        returnStringBuilder.append("CurrencyResponse{");

        for (Statement s : statementList)
            returnStringBuilder.append("\n").append(s.toString());

        returnStringBuilder.append("\n").append(error.toString());

        return returnStringBuilder.toString();
    }
}
