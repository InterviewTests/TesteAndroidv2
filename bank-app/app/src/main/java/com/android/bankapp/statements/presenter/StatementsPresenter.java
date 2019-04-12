package com.android.bankapp.statements.presenter;

import android.util.Log;

import com.android.bankapp.service.BankService;
import com.android.bankapp.service.ServiceGenerator;
import com.android.bankapp.statements.model.StatementResponse;
import com.android.bankapp.statements.view.StatementsActivity;
import com.android.bankapp.util.UserStateUtil;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementsPresenter implements StatementPresenterInput {

    private static final String TAG = "StatementsPresenter";
    public WeakReference<StatementsActivity> output;
    private BankService service;

    public StatementsPresenter() {
        service = ServiceGenerator.generateService(BankService.class);
    }

    @Override
    public void loadData() {

        int id = UserStateUtil.getUserAccount().getUserId();

        Call<StatementResponse> call = service.loadStatement(id);

        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatementList() != null) {
                        output.get().dataLoaded(response.body().getStatementList());
                    } else {
                        output.get().errorLoadData();
                    }
                } else {
                    output.get().errorLoadData();
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                output.get().errorLoadData();
                Log.e(TAG, t.getMessage());
            }
        });

    }
}
