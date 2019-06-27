package com.bilulo.androidtest04.data.api.statements;

import com.bilulo.androidtest04.data.model.response.StatementsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementsApi {
    @GET("/api/statements/{userId}")
    Call<StatementsResponse> getStatements(@Path("userId") int userId);
}
