package pt.felipegouveia.bankapp.domain

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.statements.model.StatementsResponse

interface StatementsRepository {
    fun getStatements(id: Int): Single<StatementsResponse>
}