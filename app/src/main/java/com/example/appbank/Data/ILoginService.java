package com.example.appbank.Data;

import com.example.appbank.Domain.Model.UserAccount;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    @POST("login")
    Call<UserAccount> Login(@Body RequestBody object);
}
