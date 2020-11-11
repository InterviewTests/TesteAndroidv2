package com.example.domain.usecases

import com.example.domain.base.ExceptionTag
import com.example.domain.base.IUseCase
import com.example.domain.base.UseCaseErrorManager
import com.example.domain.entities.LoginRequisicao
import com.example.domain.entities.LoginResposta
import com.example.domain.repositories.IBankRepository

class PerformLoginUseCase(private val repository: IBankRepository) :
    IUseCase<LoginResposta?, PerformLoginUseCase.Parametros> {
    data class Parametros(val loginRequisicao: LoginRequisicao)

    override suspend fun execute(parameters: Parametros): LoginResposta? {
        try {
            val resposta = repository.realizarLogin(parameters.loginRequisicao)
            return resposta
        } catch (excecao: Exception) {
            throw UseCaseErrorManager.tratarExcecao(
                ExceptionTag.REALIZAR_LOGIN,
                excecao
            )
        }
    }
}