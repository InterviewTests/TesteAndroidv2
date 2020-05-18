package br.com.crmm.bankapplication.data.source.remote.implementation

import br.com.crmm.bankapplication.data.source.remote.abstraction.StatementRemoteDataSource
import br.com.crmm.bankapplication.data.state.StatementDataState
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.StatementService
import br.com.crmm.bankapplication.framework.datasource.remote.common.AbstractService
import io.reactivex.rxjava3.core.Flowable

class StatementDataSourceImpl(
    private val statementService: StatementService
): AbstractService(), StatementRemoteDataSource {

    override fun fetch(userId: String): Flowable<StatementDataState> {
        return Flowable.fromCallable {
            val response = statementService.fetch(userId).execute()
            val body = response.body()

            when{
                body?.statementDataResponseDTOList != null -> {
                    StatementDataState.SuccessfullyResponseState(
                        body.statementDataResponseDTOList
                    )
                }
                body?.errorResponseDTO?.code != null -> {
                    StatementDataState.UnsuccessfullyResponseState(
                        body.errorResponseDTO
                    )
                }
                else -> StatementDataState.UnmappedErrorState
            }
        }
    }
}