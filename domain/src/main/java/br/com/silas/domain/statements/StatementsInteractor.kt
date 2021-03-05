package br.com.silas.domain.statements

import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.InteractorMaybe
import br.com.silas.domain.Schedulers
import io.reactivex.rxjava3.core.Maybe

class StatementsInteractor
    (schedulers: Schedulers, private val statementsRepository: StatementsRepository) :
    InteractorMaybe<Pair<ErrorResponse, List<Statements>>, StatementsInteractor.Request>(schedulers) {


    override fun create(request: Request): Maybe<Pair<ErrorResponse, List<Statements>>> {
        return statementsRepository.fetchStatements(request.getUserId())
    }

    inner class Request(private val userId: Int) : InteractorMaybe.Request() {
        fun getUserId() = userId
    }
}