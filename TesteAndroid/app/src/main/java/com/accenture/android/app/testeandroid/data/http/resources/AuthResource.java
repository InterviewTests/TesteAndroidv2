package com.accenture.android.app.testeandroid.data.http.resources;

import com.accenture.android.app.testeandroid.data.http.responses.UserAccountResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public interface AuthResource {
    @FormUrlEncoded
    @POST("login")
    Call<UserAccountResponse> login(@Field("user") String user, @Field("password") String password);
}
