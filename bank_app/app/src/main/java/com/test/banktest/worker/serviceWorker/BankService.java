package com.test.banktest.worker.serviceWorker;

import com.test.banktest.homeScreen.HomeResponse;
import com.test.banktest.loginScreen.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BankService {

    @FormUrlEncoded
    @POST("login")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<LoginResponse> login(@Field("user") String user, @Field("password") String password);

    @GET("statements/{idUser}")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<HomeResponse> getStatements(@Path("idUser") int idUser);
}
