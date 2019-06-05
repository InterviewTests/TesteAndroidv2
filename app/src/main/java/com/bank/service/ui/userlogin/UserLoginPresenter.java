package com.bank.service.ui.userlogin;

import android.content.Context;

import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;

import java.util.ArrayList;
import java.util.List;


public class UserLoginPresenter implements ILogin.Presenter {

    private String TAG = "USERLOGIN";

    // private FundModelTemplate template;

    private ILogin.Interactor interactor;
    private ILogin.Views views;
    private StatementList stmList;
    // private Statements stmItem;
    // private Alert alert;

    private List<StatementList> listStm = new ArrayList<>();
    private List<Statements> listItem = new ArrayList<>();
    // private List<UseAccount> listUseraAccount = new ArrayList<>();


    UserLoginPresenter(ILogin.Views views) {

        //template = new FundModelTemplate();

        //interactor = new StatmentsInteractor(this);
        //alert = new Alert();

        // this.views = views;

    }

    //@Override
    public Context getContext() {
        return (Context) views;
    }


    public void loadLogin() {
        // listItem = interactor.loadListTest("teste");
        // listStm = interactor.loadDetail("teste");

        if (listItem != null) {
            views.updateLogin(listItem);
        } else {
            loadAlert(1, getContext());
        }
    }


    public void loadAlert(int msgCode, Context context) {

        msgCode = (msgCode >= 0 && msgCode <= 5) ? msgCode : 0;
        views.updateAlert(msgCode, context);
    }
}

