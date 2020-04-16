package com.example.androidtest.domain.services

import com.example.androidtest.data.api.ResponseEntity
import com.example.androidtest.domain.entities.LoginEntity
import retrofit2.Call
import retrofit2.http.GET

interface LoginService {

    @GET("login")
    fun list(): Call<ResponseEntity>
}