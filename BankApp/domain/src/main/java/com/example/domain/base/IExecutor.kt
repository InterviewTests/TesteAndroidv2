package com.example.domain.base

interface IExecutor<T, Params> {
    suspend fun executar(parametros: Params): T
}

interface IExecutorSemRetorno<Params> {
    suspend fun executar(parametros: Params)
}