package com.accenture.bankapp.screens.dashboard

import android.content.Context
import android.widget.Toast
import com.accenture.bankapp.network.api.RetrofitBuilder
import com.accenture.bankapp.network.models.Statement
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.*

interface StatementsWorkerInput {
    suspend fun getListStatements(context: Context, userId: Int): ArrayList<Statement>
}

class StatementsWorker : StatementsWorkerInput {
    private val ioScope = CoroutineScope(Dispatchers.IO + CoroutineName("StatementsWorkerIOScope"))
    private val mainScope = CoroutineScope(Dispatchers.Main + CoroutineName("StatementsWorkerMainScope"))
    private val apiService = RetrofitBuilder.apiService

    override suspend fun getListStatements(context: Context, userId: Int): ArrayList<Statement> {
        val listStatements = withContext(ioScope.coroutineContext) {
            try {
                Timber.i("getListStatements: Trying to get statements from userId $userId")

                val response = apiService.getStatements(userId)

                return@withContext mainScope.async {
                    try {
                        if (response.isSuccessful) {
                            if (response.body()?.error?.code ?: 0 == 0) {
                                val listStatements = response.body()?.statementList as ArrayList<Statement>

                                Timber.i("${this.coroutineContext[CoroutineName]?.name}: Get successfully. Number of statements: ${listStatements.size}")

                                return@async listStatements
                            } else {
                                val error = response.body()?.error!!

                                Timber.i("${this.coroutineContext[CoroutineName]?.name}: Get failed: ${error.code}: ${error.message}")
                                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            val error = "Get failed: ${response.code()}"

                            Timber.e("${this.coroutineContext[CoroutineName]?.name}: $error")
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        }
                    } catch (t: Throwable) {
                        val error = "Error while returning the statements list"

                        Timber.e(t, "${this.coroutineContext[CoroutineName]?.name}: $error")
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }.await()
            } catch (t: Throwable) {
                val error = "Error getting statements"

                Timber.e(t, "${this.coroutineContext[CoroutineName]?.name}: $error")
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }

        return listStatements as ArrayList<Statement>
    }
}