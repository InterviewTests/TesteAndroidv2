package com.example.androidcodingtest.Connections;

import com.example.androidcodingtest.models.StatementsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementClient {

    @GET("statements/{id}")
    Call<StatementsResponse> getStatments(@Path("id") int id);
}
