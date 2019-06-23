package com.bilulo.androidtest04.data.api.statements;

import com.bilulo.androidtest04.data.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatementsApi {
    @GET("/api/statements/{userId}")
    Call<LoginResponse> getStatements();

}
