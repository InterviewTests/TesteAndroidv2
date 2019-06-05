package com.example.gabriela.testeandroidv2.interfaces;

import com.example.gabriela.testeandroidv2.model.InfoRes;
import com.example.gabriela.testeandroidv2.model.LoginRes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRetrofitCEP {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://bank-app-test.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginRes>doLogin(@Field("user") String user, @Field("password") String password);

    @GET("api/statements/1")
    Call<InfoRes>getInfoUser();

}
