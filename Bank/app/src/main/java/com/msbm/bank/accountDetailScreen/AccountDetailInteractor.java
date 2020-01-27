package com.msbm.bank.accountDetailScreen;

import com.msbm.bank.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

interface AccountDetailInteractorInput {
    void fetchStatementData(StatementRequest request);
}

public class AccountDetailInteractor implements AccountDetailInteractorInput {

    public static String TAG = AccountDetailInteractor.class.getSimpleName();

    public AccountDetailPresenterInput accountDetailPresenterInput;

    @Override
    public void fetchStatementData(StatementRequest request) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatementApi statementApi = retrofit.create(StatementApi.class);
        Call<StatementResponse> call = statementApi.fetchStatements(request.userId);
        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                accountDetailPresenterInput.presentStatementData(response.body());
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
//                accountDetailPresenterInput.statementerror();
            }
        });

    }
}
