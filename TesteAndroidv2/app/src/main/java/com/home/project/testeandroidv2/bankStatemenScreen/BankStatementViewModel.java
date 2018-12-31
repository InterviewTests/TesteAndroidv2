package com.home.project.testeandroidv2.bankStatemenScreen;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.home.project.testeandroidv2.model.BankStatement;

import java.util.List;


public class BankStatementViewModel extends AndroidViewModel {

    /*
    recebe ações da view e retorna atualizações quando necessário
     */

    public LiveData<List<BankStatement>> bankStatementList;
    private Application application;
    private BankStatementInteractor earthquakeInteractor;

    public BankStatementViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        earthquakeInteractor = new BankStatementInteractor();
        bankStatementList = earthquakeInteractor.getBankStatement(1);
    }


    public LiveData<List<BankStatement>> getBankStatementObservable() {
        return bankStatementList;
    }

    public void removeUserLogin(){
        earthquakeInteractor.removeUserLogin(application.getBaseContext());
    }

}
