package com.accenture.project.apptesteandroid.remoteData;

import com.accenture.project.apptesteandroid.model.LoginRequest;
import com.accenture.project.apptesteandroid.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest user);
}
