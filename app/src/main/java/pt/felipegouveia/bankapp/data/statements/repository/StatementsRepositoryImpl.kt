package pt.felipegouveia.bankapp.data.statements.repository

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.statements.api.StatementsService
import pt.felipegouveia.bankapp.data.statements.model.StatementsMapper
import pt.felipegouveia.bankapp.domain.StatementsRepository
import pt.felipegouveia.bankapp.domain.model.statements.Statements
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatementsRepositoryImpl @Inject constructor(
    private val statementsService: StatementsService,
    private val statementsMapper: StatementsMapper
): StatementsRepository {

    override fun getStatements(id: Int): Single<Statements> {
        return statementsService.getStatements(id).map {
            statementsMapper.mapStatementsDataEntityToDomainEntity(it)
        }
    }

}
