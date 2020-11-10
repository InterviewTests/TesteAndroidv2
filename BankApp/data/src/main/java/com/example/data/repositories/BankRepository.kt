package com.example.data.repositories

import com.example.data.api.BankApi
import com.example.data.api.base.RequestManager
import com.example.data.api.base.exceptions.ApiException
import com.example.data.api.base.exceptions.RepositoryException
import com.example.data.model.LoginRequisicaoData
import com.example.data.model.converterParaListaStatements
import com.example.data.model.converterParaLoginResposta
import com.example.domain.entidades.ListaStatements
import com.example.domain.entidades.LoginRequisicao
import com.example.domain.entidades.LoginResposta
import com.example.domain.repositorios.IBankRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BankRepository(private val api: BankApi) : IBankRepositorio {

    private val TAG: String = "FALHA_REPOSITORIO_EMPRESAS"
    override suspend fun realizarLogin(loginRequisicao: LoginRequisicao): LoginResposta? {
        return withContext(Dispatchers.IO) {
            try {
                val resposta = RequestManager.getResponse(
                    api.realizarLogin(
                        LoginRequisicaoData(loginRequisicao.usuario, loginRequisicao.senha!!)
                    ).execute()
                )

                resposta.converterParaLoginResposta()

            } catch (excecao: ApiException) {
                val mensagem = "Falha ao efetuar login com os seguintes parâmetros."
                val complemento =
                    "Parametros: ${loginRequisicao.usuario}. Exceção: ${excecao}."
                throw RepositoryException(TAG, mensagem, complemento, excecao)
            }
        }

    }

    override suspend fun listarSatements(id: Int?): ListaStatements {
        return withContext(Dispatchers.IO) {
            try {
                val resposta = RequestManager.getResponse(
                    api.listarStatementsPorUsuario(
                        id = id
                    ).execute()
                )

                resposta.converterParaListaStatements()

            } catch (excecao: ApiException) {
                val mensagem = "Falha ao resgatar statements."
                val complemento =
                    "Parametros: id do usuario: ${id}. Exceção: ${excecao}."
                throw RepositoryException(TAG, mensagem, complemento, excecao)
            }
        }
    }
}