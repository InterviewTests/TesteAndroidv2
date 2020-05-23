package br.com.bankapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.bankapp.data.source.StatementDataSource
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.domain.repository.StatementRepository
import javax.inject.Inject

class StatementRepositoryImpl @Inject constructor(
    private val statementDataSource: StatementDataSource
): StatementRepository {

    override suspend fun loadStatements(userId: Int) {
        statementDataSource.loadStatements(userId)
    }

    override fun getStatements(userId: Int): LiveData<PagedList<Statement>> {
        return statementDataSource.getStatements(userId)
    }
}