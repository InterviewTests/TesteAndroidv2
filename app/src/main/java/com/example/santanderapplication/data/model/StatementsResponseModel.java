package com.example.santanderapplication.data.model;

import java.util.List;

public class StatementsResponseModel {

    private List<StatementsModel>statementList;
    private Error error;

    public StatementsResponseModel(List<StatementsModel> statementsModelList, Error error) {
        this.statementList = statementsModelList;
        this.error = error;
    }

    public List<StatementsModel> getStatementsModelList() {
        return statementList;
    }

    public void setStatementsModelList(List<StatementsModel> statementsModelList) {
        this.statementList = statementsModelList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
