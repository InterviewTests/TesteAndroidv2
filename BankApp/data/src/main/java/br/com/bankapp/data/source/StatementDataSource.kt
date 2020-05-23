package br.com.bankapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.dao.StatementDao
import br.com.bankapp.data.mappers.toDomain
import br.com.bankapp.data.mappers.toEntity
import br.com.bankapp.domain.models.Statement
import javax.inject.Inject

class StatementDataSource @Inject constructor(
    private val apiService: BankAppApiService,
    private val statementDao: StatementDao
) {

    suspend fun loadStatements(userId: Int) {
        val statementListResponse = apiService.loadStatements(userId)
        statementDao.clearAndInsert(
            statementListResponse.statementList!!.mapIndexed { index, statementResponse ->
                statementResponse.toEntity(index.toLong(), userId)
            }
        )
    }

    fun getStatements(userId: Int): LiveData<PagedList<Statement>> {
        return statementDao.getStatements(userId).toDomain().toLiveData(10)
    }
}