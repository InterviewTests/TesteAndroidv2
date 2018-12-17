package com.example.savioguedes.testeandroid.state;

public interface StateContract {

    interface View{

        void initView();
        void fillList();
        void showProgressDialog();
        void onErroRequest();
    }

    interface Presenter{

        void getStatementsExecute();
    }
}
