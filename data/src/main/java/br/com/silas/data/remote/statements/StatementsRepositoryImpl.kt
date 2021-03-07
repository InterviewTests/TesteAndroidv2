package br.com.silas.data.remote.statements

import br.com.silas.data.remote.api.ServiceTesteAndroidV2
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.statements.Statements
import br.com.silas.domain.statements.StatementsRepository
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class StatementsRepositoryImpl(
    private val serviceTesteAndroidv2: ServiceTesteAndroidV2,
    private val statementMapper: StatementMapper
) : StatementsRepository {
    override fun fetchStatements(userId: Int): Single<Pair<ErrorResponse, List<Statements>>> {
        return serviceTesteAndroidv2.fetchStatements(userId)
            .map { statementMapper.mapperStatementsEntityToStatements(it) }

    }
}