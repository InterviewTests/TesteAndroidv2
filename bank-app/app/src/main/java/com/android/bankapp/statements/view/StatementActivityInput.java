package com.android.bankapp.statements.view;

import com.android.bankapp.statements.model.Statement;

import java.util.List;

public interface StatementActivityInput {

    void dataLoaded(List<Statement> statementList);
    void errorLoadData();
}
