package com.example.domain.executores

import com.example.domain.base.GerenciadorErroExecutor
import com.example.domain.base.IExecutor
import com.example.domain.base.TagExcecao
import com.example.domain.entidades.LoginRequisicao
import com.example.domain.entidades.LoginResposta
import com.example.domain.repositorios.IBankRepositorio
import java.lang.Exception

class RealizarLoginExecutor(private val repositorio: IBankRepositorio) :
    IExecutor<LoginResposta?, RealizarLoginExecutor.Parametros> {
    data class Parametros(val loginRequisicao: LoginRequisicao)

    override suspend fun executar(parametros: Parametros): LoginResposta? {
        try {
            val resposta = repositorio.realizarLogin(parametros.loginRequisicao)
            return resposta
        } catch (excecao: Exception) {
            throw GerenciadorErroExecutor.tratarExcecao(
                TagExcecao.REALIZAR_LOGIN,
                excecao
            )
        }
    }
}