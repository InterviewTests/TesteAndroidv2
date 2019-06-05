package com.bank.service.ui.statements;

import android.content.Context;

import java.sql.Statement;
import java.util.List;

import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;

public interface IStatements {


    interface Presenter{

        void onSuccess(StatementList lis);
        void onError(String message, int code);
        void loadList();

    }

    interface Interactor{


        List<Statements> loadList(String areaApp);
        //void onError(String message, int code);

    }

    interface Views{

        void loadRecView();
        void updateRecView(StatementList listObj );
        int checkRecView();
        void updateAlert(String message, int code);
    }




}
