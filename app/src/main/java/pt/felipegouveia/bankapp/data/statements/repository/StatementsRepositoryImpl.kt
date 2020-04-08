package pt.felipegouveia.bankapp.data.statements.repository

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.statements.api.StatementsService
import pt.felipegouveia.bankapp.data.statements.model.StatementsResponse
import pt.felipegouveia.bankapp.domain.StatementsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatementsRepositoryImpl @Inject constructor(
    private val statementsService: StatementsService
): StatementsRepository {

    override fun getStatements(id: Int): Single<StatementsResponse> {
        return statementsService.getStatementsList(id)
    }

}
