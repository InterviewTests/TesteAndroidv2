package com.bank.testeandroidv2.statementScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiStatementModel {
    @SerializedName("list")
    @Expose
    private List<StatementViewModel> list;
    @SerializedName("statementList")
    @Expose
    private ArrayList<StatementModel> statementList;

    public List<StatementViewModel> getList() {
        return list;
    }

    public void setList(List<StatementViewModel> list) {
        this.list = list;
    }

    public ArrayList<StatementModel> getStatementList() {
        return statementList;
    }

    public void setStatementList(ArrayList<StatementModel> statementList) {
        this.statementList = statementList;
    }
}
