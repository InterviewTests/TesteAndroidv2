package com.valber.domain2

import com.valber.data.model.Statement
import com.valber.data.platform.NetworkHandler
import com.valber.data.repository.BankRepository
import io.reactivex.Single

class StatementCase(
    private val repository: BankRepository,
    private val networkHandler: NetworkHandler
) {

    fun getStatements(id: Int): Single<List<Statement>> {
        return when (networkHandler.isConnected) {
            true -> repository.statement(id)
            false, null -> Single.error(Throwable("No internet"))
        }
    }
}