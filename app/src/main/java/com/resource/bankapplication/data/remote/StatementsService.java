package com.resource.bankapplication.data.remote;

import com.resource.bankapplication.data.remote.dto.SpentDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatementsService {

    @GET("statements/{idUser}")
    Call<SpentDto> spentList(@Path("idUser") long id);
}
