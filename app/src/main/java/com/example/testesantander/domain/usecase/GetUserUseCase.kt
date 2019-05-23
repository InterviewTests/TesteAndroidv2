package com.example.testesantander.domain.usecase

import com.example.testesantander.data.service.Service
import com.example.testesantander.domain.model.UserResponse
import retrofit2.Call

class GetUserUseCase(private val service: Service): IGetUserUseCase{
    override fun execute(user: String, password: String): Call<UserResponse> {
        return service.getUser(user, password)
    }
}