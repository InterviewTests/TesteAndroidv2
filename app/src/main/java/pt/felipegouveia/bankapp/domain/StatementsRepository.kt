package pt.felipegouveia.bankapp.domain

import io.reactivex.Flowable
import pt.felipegouveia.bankapp.domain.model.statements.Statements

interface StatementsRepository {
    fun getStatements(userId: Int): Flowable<Statements>
}