package com.rafaelpereiraramos.testeandroidv2.repo.impl

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.api.ApiResponseLiveData
import com.rafaelpereiraramos.testeandroidv2.api.BankApiService
import com.rafaelpereiraramos.testeandroidv2.api.StatementsResponse
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors
import com.rafaelpereiraramos.testeandroidv2.db.dao.StatementDao
import com.rafaelpereiraramos.testeandroidv2.db.model.StatementTO
import com.rafaelpereiraramos.testeandroidv2.repo.NetworkBoundResource
import com.rafaelpereiraramos.testeandroidv2.repo.ResourceWrapper
import com.rafaelpereiraramos.testeandroidv2.repo.StatementRepo
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 27/11/2018.
 */
class StatementRepoImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val statementDao: StatementDao,
    private val bankApiService: BankApiService
) : StatementRepo {

    override fun getStatementsByUserId(id: Int): LiveData<ResourceWrapper<List<StatementTO>>> {
        return object : NetworkBoundResource<StatementsResponse, List<StatementTO>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<StatementTO>?> = statementDao.getStatementsByUserId(id)

            override fun shouldFetch(result: List<StatementTO>?): Boolean = result == null || result.isEmpty()

            override fun makeCall(): ApiResponseLiveData<StatementsResponse> = bankApiService.getStatements(id)

            override fun saveCallResult(callResult: StatementsResponse) {
                val statements: List<StatementTO> = callResult.statementList

                statements.forEach { statement ->
                    statementDao.insert(StatementTO(statement, id))
                }
            }

        }.result
    }
}