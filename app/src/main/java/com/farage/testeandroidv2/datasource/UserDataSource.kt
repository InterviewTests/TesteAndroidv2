package com.farage.testeandroidv2.datasource

import com.farage.testeandroidv2.datasource.api.UserApi
import com.farage.testeandroidv2.datasource.model.UserResponse
import com.farage.testeandroidv2.datasource.retrofit.RetrofitConfiguration
import com.farage.testeandroidv2.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(private val retrofitConfiguration: RetrofitConfiguration) {

    suspend fun doUserLogin(): ResultState<UserResponse>? {
        var result: ResultState<UserResponse>? = ResultState.emptyData<UserResponse>()

        withContext(Dispatchers.IO) {
            try {
                val retrofitInstance = retrofitConfiguration.instance
                val userService = retrofitInstance.create(UserApi::class.java)
                val request = userService.userLogin("user", "senha")
                val response = request?.await()

                request.let {
                    when {
                        it.isCompleted -> result = ResultState.success(response)
                        it.isCancelled -> result = ResultState.error("Erro ao logar")
                    }
                }

            } catch (ex: Exception) {
                result = ex.message?.let { ResultState.error(it) }
            }
        }

        return result
    }

}