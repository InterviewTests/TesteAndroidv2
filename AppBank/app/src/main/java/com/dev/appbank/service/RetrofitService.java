package com.dev.appbank.service;

import com.dev.appbank.model.Statement;
import com.dev.appbank.model.StatementList;
import com.dev.appbank.model.User;
import com.dev.appbank.model.UserAccount;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface RetrofitService {

    @FormUrlEncoded
    @POST("login")
    Call<User> signIn(@Field("user") String user, @Field("password") String password);

    @GET("statements/1")
    Call<StatementList> getStatetments();


}
