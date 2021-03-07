package br.com.silas.domain.statements

import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.InteractorCompletable
import br.com.silas.domain.InteractorSingle
import br.com.silas.domain.Schedulers
import io.reactivex.rxjava3.core.Single

class StatementsInteractor
    (private val statementsRepository: StatementsRepository, schedulers: Schedulers) :
    InteractorSingle<Pair<ErrorResponse, List<Statements>>, StatementsInteractor.Request>(schedulers) {


    override fun create(request: Request): Single<Pair<ErrorResponse, List<Statements>>> {
        return statementsRepository.fetchStatements(request.getUserId())
    }

    inner class Request(private val userId: Int) : InteractorCompletable.Request() {
        fun getUserId() = userId
    }
}