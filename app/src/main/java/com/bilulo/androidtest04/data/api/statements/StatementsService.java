package com.bilulo.androidtest04.data.api.statements;

import com.bilulo.androidtest04.data.api.retrofit.RetrofitInitializer;

public class StatementsService {
    private RetrofitInitializer retrofitInitializer = RetrofitInitializer.getInstance();
    private StatementsApi statementsApi = retrofitInitializer.getStatementsApi();
}
