package com.example.testeandroidv2.Service;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit.Call;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

public interface StatementService {

    @GET("statements/{id}")
    Call<JsonObject> getStatements(@Path("id") int userId);
}
