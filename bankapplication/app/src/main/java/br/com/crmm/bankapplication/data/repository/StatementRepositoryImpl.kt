package br.com.crmm.bankapplication.data.repository

import br.com.crmm.bankapplication.data.source.remote.abstraction.StatementRemoteDataSource
import br.com.crmm.bankapplication.data.state.StatementDataState
import br.com.crmm.bankapplication.domain.repository.StatementRepository
import io.reactivex.rxjava3.core.Flowable

class StatementRepositoryImpl(
    private val statementRemoteDataSource: StatementRemoteDataSource
): StatementRepository {

    override fun fetch(userId: String): Flowable<StatementDataState> {
        return statementRemoteDataSource.fetch(userId)
    }

}