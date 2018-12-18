package com.example.savioguedes.testeandroid.state;

import com.example.savioguedes.testeandroid.model.StatementList;

import java.util.List;

public interface StateContract {

    interface View{

        void initView();
        void fillList(List<StatementList> statementLists);
        void showProgressDialog();
        void onErroRequest();
    }

    interface Presenter{

        void getStatementsExecute(String id);
    }
}
