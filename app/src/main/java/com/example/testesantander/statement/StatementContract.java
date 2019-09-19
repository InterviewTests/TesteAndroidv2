package com.example.testesantander.statement;

import android.content.Context;

import java.util.List;

import com.example.testesantander.model.Statement;

public class StatementContract {
    public interface View{

        Context getActivity();

        void showList(List<Statement> statementList);

        void navigateToLogin();


    }
    public interface Presenter{
        void getStatement(long id);

        void showDialogExit();

    }
}
