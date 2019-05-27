package com.caique.everis.testeandroid.data.remote;

import com.caique.everis.testeandroid.model.AccountInfoResponse;
import com.caique.everis.testeandroid.model.Login;
import com.caique.everis.testeandroid.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CallApi {

    @POST("login")
    Call<LoginResponse> getLogin(@Body Login login);

    @GET("statements/1")
    Call<AccountInfoResponse> getAccountInfo(@Header("Content-Type") String header);
}
