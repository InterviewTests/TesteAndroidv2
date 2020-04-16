package com.example.androidtest.domain.repositories

import com.example.androidtest.data.api.ResponseEntity
import com.example.androidtest.domain.entities.LoginEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeRepository {

    @Headers("Content-Type: application/json")
    @GET("statements/1")
    fun getList(): Call<ResponseEntity>
}