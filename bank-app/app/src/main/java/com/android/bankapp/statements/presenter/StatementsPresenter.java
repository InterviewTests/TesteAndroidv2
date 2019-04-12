package com.android.bankapp.statements.presenter;

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
                output.get().dataLoaded(response.body().getStatementList());
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {

            }
        });

    }
}
