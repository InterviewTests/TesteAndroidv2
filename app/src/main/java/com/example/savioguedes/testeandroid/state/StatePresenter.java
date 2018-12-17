package com.example.savioguedes.testeandroid.state;

import android.content.Context;

public class StatePresenter implements StateContract.Presenter {

    private StateContract.View view;
    private Context context;

    StatePresenter(StateContract.View view, Context context){

        this.view = view;
        this.context = context;

        view.initView();
    }

    @Override
    public void getStatementsExecute() {

    }
}
