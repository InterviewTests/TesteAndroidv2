package com.br.myapplication.repository

import com.br.myapplication.model.Statement
import com.br.myapplication.service.ApiResult
import com.br.myapplication.service.Requests
import java.lang.Exception

class HomeRepository(private val requests: Requests) {

    fun getStatements(id: String) : ApiResult<List<Statement>>{
        return try {
            val result = requests.getStatements(id).execute()
            if (result.isSuccessful) { result.body()?.let { return ApiResult.Success(it.statementList) } }
            ApiResult.Error(Throwable("Erro genérico de API"))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}