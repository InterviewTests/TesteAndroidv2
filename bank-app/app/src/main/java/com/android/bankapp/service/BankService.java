package com.android.bankapp.service;

import com.android.bankapp.login.LoginRequest;
import com.android.bankapp.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BankService {

    @POST("/api/login/")
    Call<LoginResponse> doLogin(@Body LoginRequest request);
}
