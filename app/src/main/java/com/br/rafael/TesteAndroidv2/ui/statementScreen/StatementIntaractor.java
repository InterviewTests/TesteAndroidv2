package com.br.rafael.TesteAndroidv2.ui.statementScreen;

import com.br.rafael.TesteAndroidv2.data.api.StatementApi;
import com.br.rafael.TesteAndroidv2.data.model.LoginResponse;
import com.br.rafael.TesteAndroidv2.data.model.StatementResponse;
import com.br.rafael.TesteAndroidv2.data.retrofit.RetrofitConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface StatementIntaractorInput {

    void informationLogin(LoginResponse response);
}

public class StatementIntaractor implements  StatementIntaractorInput{

    public StatementPresenterInput output;

    @Override
    public void informationLogin(LoginResponse response) {


        output.visibleProgressBar();
        StatementApi endpoint = RetrofitConfiguration.Companion.getRetrofitInstance().create(StatementApi.class);

        endpoint.getStatements(response.getLogin().getUserId()).enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                output.hideProgressBar();
                output.responselistStatemntMetaData(response.body());
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                output.hideProgressBar();

            }
        });
    }
}
