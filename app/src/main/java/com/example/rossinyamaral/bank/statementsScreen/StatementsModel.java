package com.example.rossinyamaral.bank.statementsScreen;

import com.example.rossinyamaral.bank.model.StatementModel;

import java.util.List;

public class StatementsModel {
}

class StatementsViewModel {
    //filter to have only the needed data
    List<StatementModel> statements;
}

class StatementsRequest {
    int userId;
}

class StatementsResponse {
    List<StatementModel> statements;
}
