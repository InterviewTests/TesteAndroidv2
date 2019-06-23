package com.example.appbank.data.remote;

import com.example.appbank.data.remote.model.LoginRequest;
import com.example.appbank.data.remote.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    @POST("login")
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);
}
