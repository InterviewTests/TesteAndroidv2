package com.example.santander.viewmodel;

import com.example.santander.model.loginVO;
import com.example.santander.model.statementListVO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    @FormUrlEncoded
    @POST("login")
    Call<loginVO> getLogin(@Field("user") String username,
                           @Field("password") String password);

    @GET("statement/1")
    Call<statementListVO> getStatementList(@Field("userId") Integer userId);

}
