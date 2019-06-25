package com.farage.testeandroidv2.datasource

import com.farage.testeandroidv2.datasource.api.StatementsApi
import com.farage.testeandroidv2.datasource.model.StatementRequest
import com.farage.testeandroidv2.datasource.retrofit.RetrofitConfiguration
import com.farage.testeandroidv2.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatementDataSource(private var retrofitConfiguration: RetrofitConfiguration) {

    suspend fun getAllStatements(id: Int): ResultState<StatementRequest>? {
        var result = ResultState.emptyData<StatementRequest>()

        withContext(Dispatchers.IO) {
            try {
                val retrofitInstance = retrofitConfiguration.instance
                val statementService = retrofitInstance.create(StatementsApi::class.java)
                val request = statementService.getAllStatements(id)
                val response = request.await()

                request.let {
                    when {
                        it.isCompleted -> result = ResultState.success(response)
                        it.isCancelled -> result = ResultState.error("Erro ao carregar statements")
                    }
                }
            } catch (ex: Exception) {
                result = ResultState.error(ex.message!!)
            }
        }
        return result

    }

}