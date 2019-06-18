package com.everis.TesteAndroidv2.Extrato.Model;

import com.everis.TesteAndroidv2.Base.Model.Error;

import java.util.ArrayList;

public class Lancamento {
    private ArrayList<Statements> statementList;
    private Error error;

    public ArrayList<Statements> getStatementList() {
        return statementList;
    }

    public Error getError() {
        return error;
    }
}