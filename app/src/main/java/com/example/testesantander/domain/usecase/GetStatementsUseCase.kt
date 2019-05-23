package com.example.testesantander.domain.usecase

import com.example.testesantander.data.service.Service
import com.example.testesantander.domain.model.StatementsResponse
import retrofit2.Call

class GetStatementsUseCase(private val  service: Service): IGetStatementsUseCase{

    override fun execute(): Call<StatementsResponse> {
        return service.getStatements()
    }
}