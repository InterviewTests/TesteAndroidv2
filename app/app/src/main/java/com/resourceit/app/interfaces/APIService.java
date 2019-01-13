package com.resourceit.app.interfaces;

import com.resourceit.app.models.LoginModel;
import com.resourceit.app.models.StatmentModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @GET("statements/{id}")
    Call<StatmentModel> GetStatments(@Path("id") String id);



    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> DoLogin(@Field("user") String user, @Field("password") String password);

}
