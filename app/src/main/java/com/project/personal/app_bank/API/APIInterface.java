package com.project.personal.app_bank.API;

import com.project.personal.app_bank.models.LoginRequest;
import com.project.personal.app_bank.models.StatementList;
import com.project.personal.app_bank.models.Statements;
import com.project.personal.app_bank.models.User;
import com.project.personal.app_bank.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @POST("login")
    Call<UserResponse> createPost(@Body LoginRequest loginRequest);

    @GET("statements/{id}")
    Call<StatementList> getList(@Path("id") String id);
}
