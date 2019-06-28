package com.example.appbank.ui.account;

import com.example.appbank.data.remote.model.Statement;

import java.util.List;

public class AccountContract {

    interface ListStatementView{

        void showList(List<Statement> statements);

        void showError();

    }

    interface ListStatementPresenter{

        void getStatement(int id);
    }
}
