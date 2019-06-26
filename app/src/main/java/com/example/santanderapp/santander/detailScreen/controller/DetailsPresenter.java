package com.example.santanderapp.santander.detailScreen.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.santanderapp.santander.R;
import com.example.santanderapp.santander.detailScreen.IDetailsActivity;
import com.example.santanderapp.santander.detailScreen.interfaceService.StatementsService;
import com.example.santanderapp.santander.detailScreen.model.RequestStatement;
import com.example.santanderapp.santander.detailScreen.model.ResponseStatement;
import com.example.santanderapp.santander.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsPresenter implements IDetailsActivity {

    private Context context;
    private IDetailsPresenter iDetailsPresenter;

    public DetailsPresenter(Context context, IDetailsPresenter iDetailsPresenter) {
        this.context = context;
        this.iDetailsPresenter = iDetailsPresenter;
    }

    @Override
    public void callAPI(String userId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StatementsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StatementsService service = retrofit.create(StatementsService.class);

        RequestStatement requestStatement = new RequestStatement();
        requestStatement.user = userId;

        Call<ResponseStatement> requestCatalog = service.listStat(requestStatement.user);

        requestCatalog.enqueue(new Callback<ResponseStatement>() {
            @Override
            public void onResponse(Call<ResponseStatement> call, Response<ResponseStatement> response) {
                if (!response.isSuccess()) {
                    Toast.makeText(context, context.getString(R.string.error) + response.code(), Toast.LENGTH_SHORT).show();

                } else {

                    if (response.body() != null) {
                        ResponseStatement responseStatement = response.body();
                        //manda o responseStatement
                        iDetailsPresenter.populationScreen(responseStatement);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseStatement> call, Throwable t) {
                if (!Utils.isConected(context)) {
                    Toast.makeText(context, context.getString(R.string.notConnectInternet), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, context.getString(R.string.fail) + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
