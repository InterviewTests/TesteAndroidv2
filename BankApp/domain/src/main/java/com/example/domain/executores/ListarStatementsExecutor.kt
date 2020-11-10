package com.example.domain.executores

import com.example.domain.base.GerenciadorErroExecutor
import com.example.domain.base.IExecutor
import com.example.domain.base.TagExcecao
import com.example.domain.entidades.ListaStatements
import com.example.domain.repositorios.IBankRepositorio
import java.lang.Exception

class ListarStatementsExecutor(private val repositorio : IBankRepositorio) :
    IExecutor<ListaStatements, ListarStatementsExecutor.Parametros> {
    data class Parametros(val id : Int)

    override suspend fun executar(parametros: Parametros): ListaStatements {
        try{
            val resposta = repositorio.listarSatements(parametros.id)
            return resposta
        }catch (excecao : Exception){
            throw GerenciadorErroExecutor.tratarExcecao(
                TagExcecao.LISTAR_STATEMENTS_POR_USUARIO,
                excecao
            )
        }
    }
}