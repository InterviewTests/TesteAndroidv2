package com.bank.service.ui.statements;

import android.content.Context;

import java.sql.Statement;
import java.util.List;

import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;

public interface IStatements {


    interface Presenter{

        void loadAlert(int msgCode, Context context);
        void  loadList();
        //void checkOptionList();
    }

    interface Interactor{

        //List<StatementList> loadDetail(String areaApp);
       // List<StatementList> loadList(String areaApp);
        List<Statements> loadListTest(String areaApp);



    }

    interface Views{

        void loadRecView();
        void updateRecView(List<Statements> list);
        int checkRecView();
        void updateAlert(int msgCode, Context context);
    }




}
