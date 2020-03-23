package com.bankapp.statementScreen;

import com.bankapp.ErrorResponse;

import java.util.ArrayList;

public class StatementModel {
    public String title;
    public String desc;
    public String date;
    public String value;
}

class StatementViewModel {
    public ArrayList<StatementModel> listStatementModel;
    public ErrorResponse error;
}

class StatementRequest {
    public long userId;
}

