package com.example.data.api.base

import android.content.res.Resources
import com.example.data.api.base.GsonUtil.fromJson
import com.example.data.api.base.excecoes.ApiException
import com.example.data.api.base.excecoes.RepositorioException
import com.example.data.api.base.excecoes.SucessoComRetornoNuloException
import retrofit2.Response
import java.util.concurrent.TimeoutException

object GerenciadorRequisicao {
    @Throws(RepositorioException::class)
    fun <T> obterResposta(response: Response<T>): T {
        return obterRespostaNullable(response) ?: throw SucessoComRetornoNuloException()
    }

    @Throws(RepositorioException::class)
    fun <T> obterRespostaNullable(response: Response<T>): T? {
        if (response.isSuccessful) {
            return response.body()
        } else {
            when (response.code()) {
                404 -> throw Resources.NotFoundException()
                504 -> throw TimeoutException()
            }
            val msg = try {
                 "Ocorreu um erro indefinido ao realizar a operação."
            } catch (e: Exception) {
                response.message()
            }
            throw ApiException(msg)
        }
    }
}