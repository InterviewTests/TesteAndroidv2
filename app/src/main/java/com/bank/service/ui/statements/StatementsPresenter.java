package com.bank.service.ui.statements;

import android.content.Context;
import android.util.Log;

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

    public List<StatementList> listStm = new ArrayList<>();
    public List<Statements> listItens = new ArrayList<>();


     StatementsPresenter(IStatements.Views views) {

        interactor = new StatmentsInteractor(this);
        this.views = views;

    }


    public void loadList(){

        // listItem = interactor.loadListTest("teste");
        listItens = interactor.loadList("teste");
        Log.e(TAG,"P/loadList/interactor" );

    }


    public void onSuccess(StatementList listObj){
        Log.e(TAG, "P/onSucess/teste=" + listObj.getList().size());
        if(listObj!=null){


            views.loadViews("Lista carregada com sucesso",1);
            views.updateRecView(listObj);
        }else{
            views.loadViews("Algo de Errado!",0);

        }
    }

    /**
     *  Codigo=0 erro, codigo=1 sucesso
     *
     * @param message
     * @param code
     */


    public void onError(String message, int code){
        views.loadViews("Algo de Errado!",0);
       // Log.e(TAG, "P/onError/teste=" + message);
    }


    //@Override
    public Context getContext() {
        return (Context) views;
    }



}
