package com.msbm.bank.accountDetailScreen;

import com.msbm.bank.entities.Statement;

import java.util.List;

public class AccountDetailModel {
}

class AccountDetailViewModel {
    List<Statement> statements;
}

class StatementRequest {
    String userId;
}

class StatementResponse {
    List<Statement> statements;
}
