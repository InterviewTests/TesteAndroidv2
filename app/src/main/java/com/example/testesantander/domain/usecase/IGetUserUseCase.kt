package com.example.testesantander.domain.usecase

import com.example.testesantander.domain.model.UserResponse
import retrofit2.Call

interface IGetUserUseCase{
    fun execute(user: String, password: String) : Call<UserResponse>
}