package com.example.appbank.data.remote.contract;

import com.example.appbank.data.remote.model.LoginRequest;
import com.example.appbank.data.remote.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginEndpoint {
    @POST("login")
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);
}
