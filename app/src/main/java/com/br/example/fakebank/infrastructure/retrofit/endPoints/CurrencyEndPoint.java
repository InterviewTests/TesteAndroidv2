package com.br.example.fakebank.infrastructure.retrofit.endPoints;

import com.br.example.fakebank.infrastructure.retrofit.responses.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyEndPoint {
    @GET("statements/{userId}")
    Call<CurrencyResponse> getListStatements(@Path("userId") Integer userId);
}
