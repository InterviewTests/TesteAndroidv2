package br.com.rms.bankapp.data.repository.statement

import br.com.rms.bankapp.data.local.database.dao.StatementDao
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import br.com.rms.bankapp.data.remote.model.StatementResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatementRepository @Inject constructor(
    private val statementDao: StatementDao,
    private val apiService: BankAppApiService
): StatementRepositoryContract {


    override fun loadRemoteStatement(nextPage: Int): Single<StatementResponse> {
        return apiService.getStatement(nextPage)
    }


}