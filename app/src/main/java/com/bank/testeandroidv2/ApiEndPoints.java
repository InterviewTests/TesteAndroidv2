package com.bank.testeandroidv2;

import com.bank.testeandroidv2.loginScreen.ApiLoginModel;
import com.bank.testeandroidv2.loginScreen.LoginModel;
import com.bank.testeandroidv2.statementScreen.ApiStatementModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {

    @POST("login")
    Call<ApiLoginModel> postLogin(@Body LoginModel login);

    @GET("statements/{userId}")
    Call<ApiStatementModel> getStatement(@Path("userId") String userId);
}
