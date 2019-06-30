package com.example.androidcodingtest.Statements;

import com.example.androidcodingtest.models.Statement;

import java.util.List;

public interface StatementInteractor {
    interface View{
        void generateStatementList(List<Statement> statementList);

        void showMessage(int message);
    }

    interface Presenter{
        void getStatements(int userId);
    }
}
