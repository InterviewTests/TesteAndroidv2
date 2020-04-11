package pt.felipegouveia.bankapp.domain.interactors

import io.reactivex.Single
import pt.felipegouveia.bankapp.domain.StatementsRepository
import pt.felipegouveia.bankapp.domain.model.statements.Statements
import javax.inject.Inject

class StatementsUseCase @Inject constructor(
    private val statementsRepository: StatementsRepository
) {

    fun getStatements(userId: Int): Single<Statements> {
        return statementsRepository.getStatements(userId)
    }

}