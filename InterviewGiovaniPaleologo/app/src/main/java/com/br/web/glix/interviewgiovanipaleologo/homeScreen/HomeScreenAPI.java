package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface HomeScreenAPI {

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("statements/{userId}")
    Call<HomeScreenResponse> fetchStatements(@Path("userId") String userId);
}
