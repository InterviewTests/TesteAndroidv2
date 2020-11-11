package com.example.domain.usecases

import com.example.domain.base.UseCaseErrorManager
import com.example.domain.base.ExceptionTag
import com.example.domain.base.IUseCase
import com.example.domain.entities.ListaStatements
import com.example.domain.repositories.IBankRepository
import java.lang.Exception

class ListStatementsUseCase(private val repository: IBankRepository) :
    IUseCase<ListaStatements, ListStatementsUseCase.Parametros> {
    data class Parametros(val id: Int)

    override suspend fun execute(parameters: Parametros): ListaStatements {
        try {
            val resposta = repository.listarSatements(parameters.id)
            return resposta
        } catch (excecao: Exception) {
            throw UseCaseErrorManager.tratarExcecao(
                ExceptionTag.LISTAR_STATEMENTS_POR_USUARIO,
                excecao
            )
        }
    }
}