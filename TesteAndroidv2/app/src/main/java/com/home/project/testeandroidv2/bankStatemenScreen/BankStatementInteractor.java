package com.home.project.testeandroidv2.bankStatemenScreen;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.home.project.testeandroidv2.model.BankStatement;

import java.util.List;

interface BankStatementInteractorInput {

    LiveData<List<BankStatement>> getBankStatement(int userId);

    void removeUserLogin(Context context);

}

public class BankStatementInteractor implements BankStatementInteractorInput {

    /*

    Classe que recupera os dados ou informações do BankStatementWorker e retorna ao viewModel e quando necessário
    atualização da view

     */


    public BankStatementWorkerInput bankStatementWorkerInput;

    public BankStatementWorkerInput getBankStatementWorkerInput() {
        if (bankStatementWorkerInput == null) return new BankStatementWorker();
        return bankStatementWorkerInput;
    }

    @Override
    public LiveData<List<BankStatement>> getBankStatement(int userId) {
        bankStatementWorkerInput = getBankStatementWorkerInput();
        LiveData<List<BankStatement>> liveData = bankStatementWorkerInput.getBankStatement(userId);
        return liveData;
    }


    @Override
    public void removeUserLogin(Context context) {
        bankStatementWorkerInput = getBankStatementWorkerInput();
        bankStatementWorkerInput.removeUserLogin(context);
    }
}
