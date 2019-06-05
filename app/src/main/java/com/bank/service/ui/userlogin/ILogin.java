package com.bank.service.ui.userlogin;

import android.content.Context;
import com.bank.service.ui.statements.domain.model.Statements;
import java.util.List;

public interface ILogin {


    interface Presenter{

        void loadAlert(int msgCode, Context context);
        void  loadLogin();
        //void checkOptionList();
    }

    interface Interactor{

        //List<StatementList> loadDetail(String areaApp);
       // List<StatementList> loadList(String areaApp);
       // List<Statements> loadListTest(String areaApp);

    }

    interface Views{

        void updateLogin(List<Statements> list);
        boolean checkLogin();
        void updateAlert(int msgCode, Context context);
    }




}
