package com.android.bankapp.statements;

import com.android.bankapp.service.BankService;
import com.android.bankapp.service.ServiceGenerator;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class StatementsPresenter implements StatementPresenterInput {

    WeakReference<StatementsActivity> output;
    private BankService service;

    public StatementsPresenter() {
        service = ServiceGenerator.generateService(BankService.class);
    }

    @Override
    public void loadData() {

        Call<StatementResponse> call = service.loadStatement(1);

        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {

            }
        });

    }
}
