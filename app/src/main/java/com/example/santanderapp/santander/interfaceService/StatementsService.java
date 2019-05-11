package com.example.santanderapp.santander.interfaceService;


import com.example.santanderapp.santander.model.ResponseStatement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface StatementsService {

    public static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    @GET("statements/{userId}")
    Call<ResponseStatement> listStat(@Path("userId") String userId);

}
