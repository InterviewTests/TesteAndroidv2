package com.example.santandertestebank.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ObjectsStatements implements Serializable {

    @SerializedName("statementList")
    private List<PaymentsStatements> paymentsStatements;

    @SerializedName("error")
    private Error errorStatement;

    public List<PaymentsStatements> getPaymentsStatements() {
        return paymentsStatements;
    }

    public void setPaymentsStatements(List<PaymentsStatements> paymentsStatements) {
        this.paymentsStatements = paymentsStatements;
    }

    public Error getErrorStatement() {
        return errorStatement;
    }

    public void setErrorStatement(Error errorStatement) {
        this.errorStatement = errorStatement;
    }
}
