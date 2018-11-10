package com.renanferrari.testeandroidv2.infrastructure;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BankApi {

  @FormUrlEncoded
  @POST("login")
  Single<LoginResponse> login(@Field("user") String user, @Field("password") String password);

  @GET("statements/{userId}")
  Single<StatementsResponse> statements(@Path("userId") int userId);
}