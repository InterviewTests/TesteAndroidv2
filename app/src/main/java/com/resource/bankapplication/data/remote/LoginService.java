package com.resource.bankapplication.data.remote;

import com.resource.bankapplication.data.remote.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {

    @POST("login")
    Call<LoginModel> login(@Path("user") String username, @Path("password") String password);
}
