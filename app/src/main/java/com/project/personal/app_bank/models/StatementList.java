package com.project.personal.app_bank.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementList {
    @SerializedName("statementList")
    @Expose
    private List<Statements> statementList;

    public List<Statements> getStatements() {
        return statementList;
    }
}
