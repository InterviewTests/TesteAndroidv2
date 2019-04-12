package com.android.bankapp.service;

import com.android.bankapp.login.LoginRequest;
import com.android.bankapp.login.LoginResponse;
import com.android.bankapp.statements.StatementResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BankService {

    @POST("/api/login/")
    Call<LoginResponse> doLogin(@Body LoginRequest request);

    @GET("api/statements/{userId}")
    Call<StatementResponse> loadStatement(@Path("userId") int userId);
}
