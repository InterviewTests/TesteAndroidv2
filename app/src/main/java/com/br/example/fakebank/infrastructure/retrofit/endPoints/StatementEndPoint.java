package com.br.example.fakebank.infrastructure.retrofit.endPoints;

import com.br.example.fakebank.infrastructure.retrofit.responses.StatementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementEndPoint {
    @GET("statements/{userId}")
    Call<StatementResponse> getListStatements(@Path("userId") Integer userId);
}
