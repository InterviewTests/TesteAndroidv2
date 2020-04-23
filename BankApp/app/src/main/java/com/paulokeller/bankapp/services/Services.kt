package com.paulokeller.bankapp.services


import com.paulokeller.bankapp.models.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Services {
    @POST("/api/login")
    fun login(@Body userDTO: UserDTO): Call<Account>
}

class UserDTO(val user:String, val password:String)