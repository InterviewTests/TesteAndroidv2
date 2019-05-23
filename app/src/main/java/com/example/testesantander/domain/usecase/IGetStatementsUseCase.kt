package com.example.testesantander.domain.usecase

import com.example.testesantander.domain.model.StatementsResponse
import retrofit2.Call

interface IGetStatementsUseCase{
    fun execute(): Call<StatementsResponse>
}