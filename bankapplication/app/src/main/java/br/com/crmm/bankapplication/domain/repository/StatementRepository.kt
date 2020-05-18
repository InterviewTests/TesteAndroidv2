package br.com.crmm.bankapplication.domain.repository

import br.com.crmm.bankapplication.data.state.StatementDataState
import io.reactivex.rxjava3.core.Flowable

interface StatementRepository {

    fun fetch(userId: String): Flowable<StatementDataState>

}