package com.accenture.android.app.testeandroid.data.http.resources;

import com.accenture.android.app.testeandroid.data.http.responses.StatementResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public interface StatementResource {
    @GET("statements/{userId}")
    Call<StatementResponse> getStatements(@Path("userId") Long userId);
}
