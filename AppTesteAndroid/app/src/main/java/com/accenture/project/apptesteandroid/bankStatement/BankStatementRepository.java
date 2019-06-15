package com.accenture.project.apptesteandroid.bankStatement;

import android.support.annotation.NonNull;

import com.accenture.project.apptesteandroid.model.BankStatementResponse;
import com.accenture.project.apptesteandroid.remoteData.BankStatementDataService;
import com.accenture.project.apptesteandroid.remoteData.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Classe responsável por buscar e manipular dados da web referentes ao BankStatement
 */

interface IBankStatementRepository {

    void getBankStatement(int userId, IBankStatementInteractor iBankStatementInteractor);

}

public class BankStatementRepository implements IBankStatementRepository {

    @Override
    public void getBankStatement(int userId, final IBankStatementInteractor iBankStatementInteractor) {

        BankStatementDataService bankStatementDataService = RetrofitConfig.getConfig().create(BankStatementDataService.class);
        Call<BankStatementResponse> call = bankStatementDataService.getBankStatementList(userId);
        call.enqueue(new Callback<BankStatementResponse>() {
            @Override
            public void onResponse(@NonNull Call<BankStatementResponse> call, @NonNull Response<BankStatementResponse> response) {
                if (response.isSuccessful()) {
                    BankStatementResponse bankStatementResponse = response.body();
                    //comunica ao bankStatementInteractor os dados retornados na requisição
                    iBankStatementInteractor.fetchBankStatementByUserIdResponseOk(bankStatementResponse.getStatementList());

                }

            }

            @Override
            public void onFailure(Call<BankStatementResponse> call, Throwable t) {
                //comunica ao bankStatementInteractor que houve um erro na requisição
                iBankStatementInteractor.fetchBankStatementByUserIdResponseError();
            }
        });
    }
}
