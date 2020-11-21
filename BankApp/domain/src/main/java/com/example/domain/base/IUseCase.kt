package com.example.domain.base

interface IUseCase<T, Params> {
    suspend fun execute(parameters: Params): T
}

interface IUseCaseNoResponse<Params> {
    suspend fun execute(parameters: Params)
}