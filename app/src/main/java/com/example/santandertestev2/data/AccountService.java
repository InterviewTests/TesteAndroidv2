package com.example.santandertestev2.data;

import com.example.santandertestev2.domain.model.Invoice;
import com.example.santandertestev2.domain.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountService {

    @GET("/api/statements/{n}")
    Call<Invoice>fetchInvoice(@Path("n")int number);

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse>login(@Field("user") String login, @Field("password") String pass);

}
