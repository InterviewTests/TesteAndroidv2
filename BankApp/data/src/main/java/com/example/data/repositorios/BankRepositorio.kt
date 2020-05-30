package com.example.data.repositorios

import com.example.data.api.BankApi
import com.example.data.api.base.GerenciadorRequisicao
import com.example.data.api.base.excecoes.ApiException
import com.example.data.api.base.excecoes.RepositorioException
import com.example.data.data.LoginRequisicaoData
import com.example.data.data.converterParaListaStatement
import com.example.data.data.converterParaListaStatements
import com.example.data.data.converterParaLoginResposta
import com.example.domain.entidades.ListaStatements
import com.example.domain.entidades.LoginRequisicao
import com.example.domain.entidades.LoginResposta
import com.example.domain.entidades.Statement
import com.example.domain.repositorios.IBankRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BankRepositorio(private val api: BankApi) : IBankRepositorio {

    private val TAG: String = "FALHA_REPOSITORIO_EMPRESAS"
    override suspend fun realizarLogin(loginRequisicao: LoginRequisicao): LoginResposta? {
        return withContext(Dispatchers.IO) {
            try {
                val resposta = GerenciadorRequisicao.obterResposta(
                    api.realizarLogin(
                        LoginRequisicaoData(loginRequisicao.usuario, loginRequisicao.senha)
                    ).execute()
                )

                resposta.converterParaLoginResposta()

            } catch (excecao: ApiException) {
                val mensagem = "Falha ao efetuar login com os seguintes parâmetros."
                val complemento =
                    "Parametros: ${loginRequisicao.usuario}. Exceção: ${excecao}."
                throw RepositorioException(TAG, mensagem, complemento, excecao)
            }
        }

    }

    override suspend fun listarSatements(id: Int?): ListaStatements {
        return withContext(Dispatchers.IO) {
            try {
                val resposta = GerenciadorRequisicao.obterResposta(
                    api.listarStatementsPorUsuario(
                        id = id
                    ).execute()
                )

                resposta.converterParaListaStatements()

            } catch (excecao: ApiException) {
                val mensagem = "Falha ao resgatar statements."
                val complemento =
                    "Parametros: id do usuario: ${id}. Exceção: ${excecao}."
                throw RepositorioException(TAG, mensagem, complemento, excecao)
            }
        }
    }
}