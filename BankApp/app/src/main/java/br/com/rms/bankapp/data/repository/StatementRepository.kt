package br.com.rms.bankapp.data.repository

import br.com.rms.bankapp.data.local.database.dao.StatementDao
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import br.com.rms.bankapp.data.remote.model.StatementResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatementRepository @Inject constructor(
    private val statementDao: StatementDao,
    private val apiService: BankAppApiService
) {

    fun loadRemoteStatement(page: Int): Single<StatementResponse> {
        return apiService.getStatement(page)
    }

}