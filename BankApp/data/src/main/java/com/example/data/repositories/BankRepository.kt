package com.example.data.repositories

import com.example.data.networking.BankApi
import com.example.data.networking.base.RequestManager
import com.example.data.networking.base.exceptions.ApiException
import com.example.data.networking.base.exceptions.RepositoryException
import com.example.data.model.LoginRequisicaoData
import com.example.data.model.toModel
import com.example.domain.entities.ListaStatements
import com.example.domain.entities.LoginRequisicao
import com.example.domain.entities.LoginResposta
import com.example.domain.repositories.IBankRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BankRepository(private val api: BankApi) : IBankRepository {

    private val TAG: String = "FALHA_REPOSITORIO_EMPRESAS"
    override suspend fun realizarLogin(loginRequisicao: LoginRequisicao): LoginResposta? {
        return withContext(Dispatchers.IO) {
            try {
                val resposta = RequestManager.getResponse(
                    api.realizarLogin(
                        LoginRequisicaoData(loginRequisicao.usuario, loginRequisicao.senha!!)
                    ).execute()
                )

                resposta.toModel()

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

                resposta.toModel()

            } catch (excecao: ApiException) {
                val mensagem = "Falha ao resgatar statements."
                val complemento =
                    "Parametros: id do usuario: ${id}. Exceção: ${excecao}."
                throw RepositoryException(TAG, mensagem, complemento, excecao)
            }
        }
    }
}