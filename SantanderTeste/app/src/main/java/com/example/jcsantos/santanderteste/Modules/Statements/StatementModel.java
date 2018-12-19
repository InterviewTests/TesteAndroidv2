package com.example.jcsantos.santanderteste.Modules.Statements;

import android.content.Context;

import com.example.jcsantos.santanderteste.Components.Objects.Statement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatementModel {
    public ArrayList<Statement> statementList;
    public ArrayList data;
    public StatementResponse rs;
    public static StatementActivity activity;
    Context context;

    public StatementModel(StatementResponse rs, Context context) {
        this.statementList = new ArrayList<>();
        this.data = new ArrayList<>();
        this.context = context;
        this. activity = new StatementActivity();
        this. rs = rs;
    }

    public void requestStatement(int userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatementService services = retrofit.create(StatementService.class);
        final Call<StatementModel> requestStatements;

        requestStatements = services.statementsService(userId);
        requestStatements.enqueue(new Callback<StatementModel>() {


            @Override
            public void onResponse(Call<StatementModel> call, Response<StatementModel> response) {
                if (response.body() != null) {
                    if (response.body().statementList != null) {
                        data = response.body().statementList;
                        rs.requestSuccess(data);
                        System.out.println(":::: response statement ::::" + response.body().statementList);

                    }

                }
            }

            @Override
            public void onFailure(Call<StatementModel> call, Throwable t) {
                // tratar erro...
                System.out.println(":: erro request statement ::" + t.getMessage());
            }
        });

    }
}
