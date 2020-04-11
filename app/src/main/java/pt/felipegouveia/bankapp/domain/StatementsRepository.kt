package pt.felipegouveia.bankapp.domain

import io.reactivex.Single
import pt.felipegouveia.bankapp.domain.model.statements.Statements

interface StatementsRepository {
    fun getStatements(userId: Int): Single<Statements>
}