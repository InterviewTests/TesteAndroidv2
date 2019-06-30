package com.example.androidcodingtest.Connections;

import com.example.androidcodingtest.models.StatmentsResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatmentClient {

    @GET("statments/{id}")
    Call<StatmentsResponse> getStatments(@Path("id") String id);
}
