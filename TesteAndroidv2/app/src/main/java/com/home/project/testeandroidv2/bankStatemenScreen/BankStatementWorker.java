package com.home.project.testeandroidv2.bankStatemenScreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.home.project.testeandroidv2.dao.UserAccountPreference;
import com.home.project.testeandroidv2.model.BankStatement;
import com.home.project.testeandroidv2.model.BankStatementResponse;
import com.home.project.testeandroidv2.service.ConfigService;
import com.home.project.testeandroidv2.service.BankStatementListDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface BankStatementWorkerInput {

    LiveData<List<BankStatement>> getBankStatement(int userId);

    void removeUserLogin(Context context);
}

public class BankStatementWorker implements BankStatementWorkerInput {


    /*
    Classe que faz o trabalho pesado, recupera os dados de um serviço web ou de uma base de dados local
     */

    @Override
    public LiveData<List<BankStatement>> getBankStatement(int userId) {
        Log.e("observable", "entrou");
        BankStatementListDataService bankStatementListDataService = ConfigService.getConfig().create(BankStatementListDataService.class);
        Call<BankStatementResponse> call = bankStatementListDataService.getBankStatementList(userId);
        final MutableLiveData<List<BankStatement>> requestMutableLiveData = new MutableLiveData<>();
        call.enqueue(new Callback<BankStatementResponse>() {
            @Override
            public void onResponse(@NonNull Call<BankStatementResponse> call, @NonNull Response<BankStatementResponse> response) {
                if (response.isSuccessful()) {
                    BankStatementResponse bankStatementResponse = response.body();
                    requestMutableLiveData.setValue(bankStatementResponse.getStatementList());
                } else {
                    requestMutableLiveData.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<BankStatementResponse> call, Throwable t) {
                requestMutableLiveData.setValue(null);
            }
        });

        return requestMutableLiveData;
    }

    @Override
    public void removeUserLogin(Context context) {
        UserAccountPreference.removeUserLogin(context);
    }
}
