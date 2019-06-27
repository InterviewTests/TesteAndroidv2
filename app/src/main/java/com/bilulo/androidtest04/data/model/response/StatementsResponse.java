package com.bilulo.androidtest04.data.model.response;

import com.bilulo.androidtest04.data.model.ErrorModel;
import com.bilulo.androidtest04.data.model.StatementModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementsResponse {
    @SerializedName("statementList")
    private List<StatementModel> statementModelList;
    @SerializedName("error")
    private ErrorModel errorModel;

    public List<StatementModel> getStatementModelList() {
        return statementModelList;
    }

    public void setStatementModelList(List<StatementModel> statementModelList) {
        this.statementModelList = statementModelList;
    }

    public ErrorModel getErrorModel() {
        return errorModel;
    }

    public void setErrorModel(ErrorModel errorModel) {
        this.errorModel = errorModel;
    }
}
