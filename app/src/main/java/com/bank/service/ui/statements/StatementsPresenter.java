package com.bank.service.ui.statements;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;
import com.bank.service.ui.statements.interactor.StatmentsInteractor;

public class StatementsPresenter implements IStatements.Presenter {

    private String TAG = "STATEMENTS";

   // private FundModelTemplate template;

    private IStatements.Interactor interactor;
    private IStatements.Views views;
    private StatementList stmList;
   // private Statements stmItem;
   // private Alert alert;

    private List<StatementList> listStm = new ArrayList<>();
    private List<Statements> listItem = new ArrayList<>();
    // private List<UseAccount> listUseraAccount = new ArrayList<>();


     StatementsPresenter(IStatements.Views views) {

        //template = new FundModelTemplate();

        interactor = new StatmentsInteractor(this);
        //alert = new Alert();

        this.views = views;

    }

    //@Override
    public Context getContext() {
        return (Context) views;
    }


    public void loadList(){
        listItem = interactor.loadListTest("teste");
        // listStm = interactor.loadDetail("teste");

        if(listItem!=null){
            views.updateRecView(listItem);
        }else{
            loadAlert(1,getContext()  );
        }
    }


    public void loadAlert(int msgCode, Context context){
        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;
        views.updateAlert(msgCode, context);
    }




}
